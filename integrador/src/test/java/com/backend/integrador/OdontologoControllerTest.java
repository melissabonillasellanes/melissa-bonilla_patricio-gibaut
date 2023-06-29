package com.backend.integrador;

import com.backend.integrador.controller.OdontologoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class OdontologoControllerTest {

    @Autowired
    private OdontologoController odontologoController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void deberíaFuncionar(){}

/*
    @Test
    @Order(2)
    void deberiaEncontrarOdontologoPorIdPorApi(){

        try {
            MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}",1))
                    .andDo(print())
                    .andExpect((ResultMatcher) content().contentType("application/json"))
                    .andExpect(status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.matricula").value(""));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

 */

}
