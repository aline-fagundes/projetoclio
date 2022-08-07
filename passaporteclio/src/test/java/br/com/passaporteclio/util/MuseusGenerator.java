package br.com.passaporteclio.util;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;

public class MuseusGenerator {

    public void cadastrarMuseu(TokenGenerator generator, MockMvc mockMvc) throws Exception {
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
                                .header("Authorization", "Bearer " + generator.obterTokenAdmin(mockMvc))
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON));
    }
}
