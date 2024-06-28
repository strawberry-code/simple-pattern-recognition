package com.acme.hip.hop.chocobo;

import com.acme.hip.hop.chocobo.geometry.Point;
import com.acme.hip.hop.chocobo.rest.ApiResponse;
import com.acme.hip.hop.chocobo.rest.PointRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Reset context after each test
public class LineControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // Inizializza il contesto di test
    }

    //@Test
    public void testClearAll() throws Exception {
        // Save points to the database
        testSavePoint();

        MvcResult result = mockMvc.perform(delete("/space")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        ApiResponse<?> response = objectMapper.readValue(responseContent, ApiResponse.class);
        assertThat(response.getResponse()).isEqualTo("All data cleared");
        assertThat(response.getTimestamp()).isNotNull();

        result = mockMvc.perform(get("/space")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        responseContent = result.getResponse().getContentAsString();
        response = objectMapper.readValue(responseContent, ApiResponse.class);
        assertThat(response.getResponse()).isEqualTo(new ArrayList<>());
        assertThat(response.getTimestamp()).isNotNull();

    }

    @Test
    public void testSavePoint() throws Exception {
        testSavePoint(1f, 6f);
        testSavePoint(2f, 7f); // Add points needed for this test
        testSavePoint(3f, 8f); // Add points needed for this test
        // testSavePoint(4f, 9f); // Add points needed for this test
        // testSavePoint(5f, 10f); // Add points needed for this test
    }


    public void testSavePoint(float x, float y) throws Exception {
        if(x == 0.0f && y == 0.0f) {
            x = 1.0f;
            y = 2.0f;
        }
        PointRequest pointRequest = new PointRequest();
        pointRequest.setX(x);
        pointRequest.setY(y);

        MvcResult result = mockMvc.perform(post("/point")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pointRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        ApiResponse<?> response = objectMapper.readValue(responseContent, ApiResponse.class);
        assertThat(response.getResponse()).isNotNull();
        assertThat(response.getTimestamp()).isNotNull();
    }

    @Test
    public void testGetLinesWithAtLeastNPoints() throws Exception {
        int n = 3;
        MvcResult result = mockMvc.perform(get("/lines/{n}", n)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        ApiResponse<?> response = objectMapper.readValue(responseContent, ApiResponse.class);
        assertThat(response.getResponse()).isNotNull();
        assertThat(response.getTimestamp()).isNotNull();

        result = mockMvc.perform(get("/space")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        responseContent = result.getResponse().getContentAsString();
        ApiResponse<List<Point>> responsePoints = objectMapper.readValue(responseContent, ApiResponse.class);
        assertThat(responsePoints.getResponse().size()).isEqualTo(4*(4-1));
        assertThat(responsePoints.getTimestamp()).isNotNull();
    }

    @Test
    public void testGetSpace() throws Exception {
        // Save points to the database
        testSavePoint();

        MvcResult result = mockMvc.perform(get("/space")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        ApiResponse<?> response = objectMapper.readValue(responseContent, ApiResponse.class);
        assertThat(response.getResponse()).isNotNull();
        assertThat(response.getTimestamp()).isNotNull();
    }

}