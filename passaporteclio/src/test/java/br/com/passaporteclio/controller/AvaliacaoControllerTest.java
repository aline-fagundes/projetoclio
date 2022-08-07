package br.com.passaporteclio.controller;

import br.com.passaporteclio.service.AvaliacaoService;
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
public class AvaliacaoControllerTest {

    private TokenGenerator tokenGenerator;

    private MuseusGenerator museuGenerator;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AvaliacaoService service;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.tokenGenerator = new TokenGenerator();
        this.museuGenerator = new MuseusGenerator();
    }

    @Test
    @Order(1)
    public void deveriaDevolver200AoCadastrarAvaliacaoComCreate() throws Exception {

        museuGenerator.cadastrarMuseu(tokenGenerator, mockMvc);
        tokenGenerator.cadastrarVisitante(mockMvc);

        URI uri = new URI("/avaliacao");
        String json = "{\r\n"
                + "    \"nota\": \"5\",\r\n"
                + "    \"avaliacao\": \"Testando criar.\",\r\n"
                + "    \"museu\": {\r\n"
                + "        \"id\": \"1\"\r\n"
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
    public void deveriaDevolver200AoBuscarTodasAsAvaliacoesComFindAll() throws Exception {
        URI uri = new URI("/avaliacao");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uri)
                                        .header("Authorization", "Bearer " + tokenGenerator.obterTokenAdmin(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String listaDeAvaliacoes = result.andReturn().getResponse().getContentAsString();
        assertFalse(listaDeAvaliacoes.isEmpty());
    }

    @Test
    @Order(3)
    public void deveriaDevolver200AoBuscarAvaliacoesPorIdComFindById() throws Exception {
        URI uri = new URI("/avaliacao/1");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uri)
                                        .header("Authorization", "Bearer " + tokenGenerator.obterTokenAdmin(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String avaliacao = result.andReturn().getResponse().getContentAsString();
        assertFalse(avaliacao.isEmpty());
    }

    @Test
    @Order(4)
    public void deveriaDevolver200AoBuscarAvaliacoesPorIdDoVisitanteComFindByIdVisitante() throws Exception {
        URI uri = new URI("/avaliacao/doVisitante/2");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uri)
                                        .header("Authorization", "Bearer " + tokenGenerator.obterTokenVisitante(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String avaliacoes = result.andReturn().getResponse().getContentAsString();
        assertFalse(avaliacoes.isEmpty());
    }

    @Test
    @Order(5)
    public void deveriaDevolver200AoAtualizarAvaliacaoComUpdate() throws Exception {
        URI uri = new URI("/avaliacao/1");
        String json = "{\"nota\":\"5\",\"avaliacao\":\"Testando atualizar.\"}";;

        mockMvc.
                perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .header("Authorization", "Bearer " + tokenGenerator.obterTokenVisitante(mockMvc))
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    @Order(6)
    public void deveriaDevolver200AoDenunciarAvaliacaoPorIdComReport() throws Exception {
        URI uri = new URI("/avaliacao/denunciar/1");

                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .post(uri)
                                        .header("Authorization", "Bearer " + tokenGenerator.obterTokenVisitante(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));
    }

    @Test
    @Order(7)
    public void deveriaDevolver200AoBuscarAvaliacoesDenunciadasComFindAllReported() throws Exception {
        URI uri = new URI("/avaliacao/denuncias");

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .get(uri)
                                        .header("Authorization", "Bearer " + tokenGenerator.obterTokenAdmin(mockMvc)))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String avaliacoesDenunciadas = result.andReturn().getResponse().getContentAsString();
        assertFalse(avaliacoesDenunciadas.isEmpty());
    }

    @Test
    @Order(8)
    public void deveriaDevolver200AoExcluirAvaliacaoComDelete() throws Exception {
        URI uri = new URI("/avaliacao/1");

        mockMvc.
                perform(
                        MockMvcRequestBuilders
                                .delete(uri)
                                .header("Authorization", "Bearer " + tokenGenerator.obterTokenAdmin(mockMvc)))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }
}
