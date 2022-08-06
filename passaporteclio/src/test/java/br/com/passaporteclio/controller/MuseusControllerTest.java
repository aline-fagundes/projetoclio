package br.com.passaporteclio.controller;

import br.com.passaporteclio.service.MuseusService;
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
public class MuseusControllerTest {

    private TokenGenerator generator;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MuseusService service;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        generator = new TokenGenerator();
    }

    @Test
    @Order(1)
    public void deveriaDevolver200AoCadastrarMuseuComCreate() throws Exception {
        URI uri = new URI("/museus");
        String json = "{\r\n"
                + "    \"nome\": \"Teste\",\r\n"
                + "    \"descricaoMuseu\": \"Criação de um museu de testes.\",\r\n"
                + "    \"funcionamentoMuseu\": \"De segunda a segunda, das 10h às 22h.\",\r\n"
                + "    \"urlFoto\": \"https://www.alura.com.br/artigos/assets/tipos-de-testes-principais-por-que-utiliza-los/imagem1.jpg\",\r\n"
                + "    \"urlSite\": \"museuteste.org.br\",\r\n"
                + "    \"urlInstagram\": \"instagram.com/museuteste\",\r\n"
                + "    \"endereco\": {\r\n"
                + "        \"cep\": \"03033030\",\r\n"
                + "        \"rua\": \"Av. Testes\",\r\n"
                + "        \"numero\": \" 0\",\r\n"
                + "        \"bairro\": \"Parque dos Testes\",\r\n"
                + "        \"cidade\": \"São Paulo\",\r\n"
                + "        \"estado\": \"SP\",\r\n"
                + "        \"pais\": \"BR\"\r\n"
                + "    }\r\n"
                + "}";

        mockMvc.
                perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .header("Authorization", "Bearer " + generator.obterToken(mockMvc))
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    @Order(2)
    public void deveriaDevolver200AoBuscarTodosOsMuseusComFindAll() throws Exception {
        URI uri = new URI("/museus");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uri)
                                        .header("Authorization", "Bearer " + generator.obterToken(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String listaDeMuseus = result.andReturn().getResponse().getContentAsString();
        assertFalse(listaDeMuseus.isEmpty());
    }

    @Test
    @Order(3)
    public void deveriaDevolver200AoBuscarMuseuPorIdComFindById() throws Exception {
        URI uri = new URI("/museus/1");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uri)
                                        .header("Authorization", "Bearer " + generator.obterToken(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String museu = result.andReturn().getResponse().getContentAsString();
        assertFalse(museu.isEmpty());
    }

    @Test
    @Order(4)
    public void deveriaDevolver200AoBuscarMuseuPorNomeComFindByNome() throws Exception {
        URI uri = new URI("/museus/buscarPorNome/Teste");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uri)
                                        .header("Authorization", "Bearer " + generator.obterToken(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String museu = result.andReturn().getResponse().getContentAsString();
        assertFalse(museu.isEmpty());
    }

    @Test
    @Order(5)
    public void deveriaDevolver200AoAtualizarMuseuComUpdate() throws Exception {
        URI uri = new URI("/museus/1");
        String json = "{\r\n"
                + "    \"nome\": \"Teste\",\r\n"
                + "    \"descricaoMuseu\": \"Atualização de um museu de testes.\",\r\n"
                + "    \"funcionamentoMuseu\": \"De segunda a segunda, das 10h às 22h.\",\r\n"
                + "    \"urlFoto\": \"https://www.alura.com.br/artigos/assets/tipos-de-testes-principais-por-que-utiliza-los/imagem1.jpg\",\r\n"
                + "    \"urlSite\": \"museuteste.org.br\",\r\n"
                + "    \"urlInstagram\": \"instagram.com/museuteste\",\r\n"
                + "    \"endereco\": {\r\n"
                + "        \"cep\": \"03033030\",\r\n"
                + "        \"rua\": \"Av. Testes\",\r\n"
                + "        \"numero\": \" 0\",\r\n"
                + "        \"bairro\": \"Parque dos Testes\",\r\n"
                + "        \"cidade\": \"São Paulo\",\r\n"
                + "        \"estado\": \"SP\",\r\n"
                + "        \"pais\": \"BR\"\r\n"
                + "    }\r\n"
                + "}";

        mockMvc.
                perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .header("Authorization", "Bearer " + generator.obterToken(mockMvc))
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }
    @Test
    @Order(6)
    public void deveriaDevolver200AoExcluirMuseuComDelete() throws Exception {
        URI uri = new URI("/museus/1");

                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .delete(uri)
                                        .header("Authorization", "Bearer " + generator.obterToken(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));
    }
}
