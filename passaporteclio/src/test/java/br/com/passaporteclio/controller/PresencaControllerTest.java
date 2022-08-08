package br.com.passaporteclio.controller;

import br.com.passaporteclio.service.PresencaService;
import br.com.passaporteclio.util.MuseusGenerator;
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
public class PresencaControllerTest {

    private TokenGenerator tokenGenerator;

    private MuseusGenerator museuGenerator;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PresencaService service;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.tokenGenerator = new TokenGenerator();
        this.museuGenerator = new MuseusGenerator();
    }

    @Test
    @Order(1)
    public void deveriaDevolver200AoCadastrarPresencaComCreate() throws Exception {

        museuGenerator.cadastrarMuseu(tokenGenerator, mockMvc);

        URI uriMuseu = new URI("/museus/buscarPorNome/Teste-Avaliacao");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uriMuseu)
                                        .header("Authorization", "Bearer " + tokenGenerator.obterTokenAdmin(mockMvc)));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        String idMuseu = jsonParser.parseMap(resultString).get("id").toString();

        tokenGenerator.cadastrarVisitante(mockMvc);

        URI uri = new URI("/presenca");
        String json = "{\r\n"
                + "    \"museu\": {\r\n"
                + "        \"id\": \"" + idMuseu + "\"\r\n"
                + "    }\r\n"
                + "}";

        mockMvc.
                perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .header("Authorization", "Bearer " + tokenGenerator.obterTokenVisitante(mockMvc))
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    @Order(2)
    public void deveriaDevolver200AoBuscarTodasAsPresencasComFindAll() throws Exception {
        URI uri = new URI("/presenca");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uri)
                                        .header("Authorization", "Bearer " + tokenGenerator.obterTokenAdmin(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String listaDePresencas = result.andReturn().getResponse().getContentAsString();
        assertFalse(listaDePresencas.isEmpty());
    }

    @Test
    @Order(3)
    public void deveriaDevolver200AoBuscarPresencaPorIdComFindById() throws Exception {
        URI uri = new URI("/presenca/1");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uri)
                                        .header("Authorization", "Bearer " + tokenGenerator.obterTokenAdmin(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String presenca = result.andReturn().getResponse().getContentAsString();
        assertFalse(presenca.isEmpty());
    }

    @Test
    @Order(4)
    public void deveriaDevolver200AoBuscarPresencasPorIdDoVisitanteComFindByIdVisitante() throws Exception {
        URI uri = new URI("/presenca/doVisitante/2");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uri)
                                        .header("Authorization", "Bearer " + tokenGenerator.obterTokenVisitante(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String presencas = result.andReturn().getResponse().getContentAsString();
        assertFalse(presencas.isEmpty());
    }
}
