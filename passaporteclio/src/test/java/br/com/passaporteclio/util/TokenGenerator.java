package br.com.passaporteclio.util;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;

public class TokenGenerator {

    public String obterTokenAdmin(MockMvc mockMvc) throws Exception {
        URI uri = new URI("/auth");
        String json = "{\"email\":\"admin@email.com\",\"senha\":\"123\"}";

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .post(uri)
                                        .content(json)
                                        .contentType(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("token").toString();
    }

    private static void cadastrarVisitante(MockMvc mockMvc) throws Exception {
        URI uri = new URI("/visitante");
        String json = "{\r\n"
                + "    \"nome\": \"Visitante\",\r\n"
                + "    \"sobrenome\": \"de Testes.\",\r\n"
                + "    \"user\": {\r\n"
                + "        \"email\": \"visitante@email.com\",\r\n"
                + "        \"senha\": \"123\"\r\n"
                + "    }\r\n"
                + "}";

        mockMvc.
                perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON));
    }

    public String obterTokenVisitante(MockMvc mockMvc) throws Exception {
        cadastrarVisitante(mockMvc);

        URI uri = new URI("/auth");
        String json = "{\"email\":\"visitante@email.com\",\"senha\":\"123\"}";

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .post(uri)
                                        .content(json)
                                        .contentType(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("token").toString();
    }
}
