package com.yeoboge.recommendation.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PersonalRecommendControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void get_personal_recommendation() throws Exception {
        mvc.perform(get("/for-me/1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
