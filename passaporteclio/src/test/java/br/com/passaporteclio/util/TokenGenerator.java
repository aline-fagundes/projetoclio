package br.com.passaporteclio.util;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

public class TokenGenerator {

    public String obterToken(MockMvc mockMvc) throws Exception {
        URI uri = new URI("/auth");
        String json = "{\"email\":\"admin@email.com\",\"senha\":\"123\"}";

        ResultActions result =
                mockMvc.
                        perform(
                                MockMvcRequestBuilders
                                        .post(uri)
                                        .content(json)
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers
                                .status()
                                .is(200));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("token").toString();
    }
}
