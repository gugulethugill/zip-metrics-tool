package com.gugulethugillz.zip_metrics_tool.controller;

import com.gugulethugillz.zip_metrics_tool.service.impl.ZipServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest
//@ExtendWith(MockitoExtension.class)

@RunWith(SpringRunner.class)
@WebMvcTest(ZipController.class)
@ExtendWith(SpringExtension.class)
public class ZipControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ZipController controller;

    @MockBean
    ZipServiceImpl service;

    @BeforeEach
    public void setUp() {
        // Reset mocks before each test
        reset(service);
    }

    @Test
    public void givenTheBidPath_whenGetRequest_thenShouldReturnPageContainingBids() throws Exception {
        mockMvc.perform(post("/api/v1/zip/get-metrics"))
                //.andExpect(view().name("bids"))
                .andExpect(status().isCreated());
    }
}

