package org.factoriaf5.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductIntegrationTests {

    @Autowired
    private MockMvc server;

    @Test
    void theServerRespondsToYourName() throws Exception {

        server.perform(post("/products/buy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{ \"name\": \"camiseta\", \"price\": \"10.00\" },{ \"name\": \"pantalón\", \"price\": \"30.00\" }]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", equalTo("Gracias por su compra. 2 productos por un total de 40 euros")));

    }



}
