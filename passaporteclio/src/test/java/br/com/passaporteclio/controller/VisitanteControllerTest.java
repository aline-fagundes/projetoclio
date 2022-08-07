package br.com.passaporteclio.controller;

import br.com.passaporteclio.service.VisitanteService;
import br.com.passaporteclio.util.TokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class VisitanteControllerTest {

    private TokenGenerator tokenGenerator;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VisitanteService service;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        tokenGenerator = new TokenGenerator();
    }

    @Test
    @Order(1)
    public void deveriaDevolver200AoCadastrarVisitanteComCreate() throws Exception {
        URI uri = new URI("/visitante");
        String json = "{\r\n"
                + "    \"nome\": \"Teste\",\r\n"
                + "    \"sobrenome\": \"Testando\",\r\n"
                + "    \"user\": {\r\n"
                + "        \"email\": \"teste@email.com\",\r\n"
                + "        \"senha\": \"123\"\r\n"
                + "    }\r\n"
                + "}";

        mockMvc.
                perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    @Order(2)
    public void deveriaDevolver200AoBuscarTodosOsVisitantesComFindAll() throws Exception {
        URI uri = new URI("/visitante");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uri)
                                        .header("Authorization", "Bearer " + tokenGenerator.obterTokenAdmin(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String listaDeVisitantes = result.andReturn().getResponse().getContentAsString();
        assertFalse(listaDeVisitantes.isEmpty());
    }

    @Test
    @Order(3)
    public void deveriaDevolver200AoBuscarVisitantePorIdComFindById() throws Exception {
        URI uri = new URI("/visitante/1");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uri)
                                        .header("Authorization", "Bearer " + tokenGenerator.obterTokenAdmin(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String visitante = result.andReturn().getResponse().getContentAsString();
        assertFalse(visitante.isEmpty());
    }

    @Test
    @Order(4)
    public void deveriaDevolver200AoAtualizarVisitanteComUpdate() throws Exception {
        URI uriAutenticacao = new URI("/auth");
        String jsonAutenticacao = "{\"email\":\"teste@email.com\",\"senha\":\"123\"}";

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .post(uriAutenticacao)
                                        .content(jsonAutenticacao)
                                        .contentType(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        String tokenVisitante = jsonParser.parseMap(resultString).get("token").toString();

        URI uri = new URI("/visitante/1");
        String json = "{\r\n"
                + "    \"nome\": \"Testando\",\r\n"
                + "    \"sobrenome\": \"Teste\"\r\n"
                + "}";

        mockMvc.
                perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .header("Authorization", "Bearer " + tokenVisitante)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    @Order(5)
    public void deveriaDevolver200AoAtualizarSenhaDoVisitanteComUpdatePassword() throws Exception {
        URI uriAutenticacao = new URI("/auth");
        String jsonAutenticacao = "{\"email\":\"teste@email.com\",\"senha\":\"123\"}";

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .post(uriAutenticacao)
                                        .content(jsonAutenticacao)
                                        .contentType(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        String tokenVisitante = jsonParser.parseMap(resultString).get("token").toString();

        URI uri = new URI("/visitante/alterar-senha/1");
        String json = "{\r\n"
                + "    \"senhaAntiga\": \"123\",\r\n"
                + "    \"senhaNova\": \"321\",\r\n"
                + "    \"confirmaSenhaNova\": \"321\"\r\n"
                + "    }\r\n"
                + "}";

        mockMvc.
                perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .header("Authorization", "Bearer " + tokenVisitante)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }
}
