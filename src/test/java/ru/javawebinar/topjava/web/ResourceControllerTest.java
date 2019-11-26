package ru.javawebinar.topjava.web;

import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ResourceControllerTest extends AbstractControllerTest {
    @Test
    public void checkCss() throws Exception {
        mockMvc.perform(get(URI.create("/resources/css/style.css")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/css"));
    }
}
