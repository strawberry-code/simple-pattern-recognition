package com.acme.hip.hop.chocobo;

import com.acme.hip.hop.chocobo.geometry.Point;
import com.acme.hip.hop.chocobo.rest.ApiResponse;
import com.acme.hip.hop.chocobo.rest.PointRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LineControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // Inizializza il contesto di test
    }

    @Test
    @Order(1)
    public void testClearAll() throws Exception {
        // Save points to the database
        testSavePoint();

        MvcResult result = mockMvc.perform(delete("/space")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        ApiResponse<?> response = objectMapper.readValue(responseContent, ApiResponse.class);
        assertThat(response.getResponse()).isEqualTo("All data cleared successfully.");
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
    @Order(2)
    public void testSavePoint() throws Exception {
        testSavePoint(1f, 6f);
        testSavePoint(2f, 7f);
        testSavePoint(3f, 8f);
        testSavePoint(4f, 9f);
        testSavePoint(5f, 10f);
    }

    @Test
    @Order(3)
    public void testGetSpace() throws Exception {
        MvcResult result = mockMvc.perform(get("/space")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        ApiResponse<List<Point>> response = objectMapper.readValue(responseContent, ApiResponse.class);
        assertThat(response.getResponse().size()).isEqualTo(5);
        assertThat(response.getTimestamp()).isNotNull();
    }

    @Test
    @Order(4)
    public void testGetLinesWithAtLeast1Points() throws Exception {
        testGetLinesWithAtLeastNPoints(1);
    }


    @Test
    @Order(5)
    public void testGetLinesWithAtLeast2Points() throws Exception {
        testGetLinesWithAtLeastNPoints(2);
    }


    @Test
    @Order(6)
    public void testGetLinesWithAtLeast3Points() throws Exception {
        testGetLinesWithAtLeastNPoints(3);
    }


    @Test
    @Order(7)
    public void testGetLinesWithAtLeast4Points() throws Exception {
        testGetLinesWithAtLeastNPoints(4);
    }


    @Test
    @Order(8)
    public void testGetLinesWithAtLeast5Points() throws Exception {
        testGetLinesWithAtLeastNPoints(5);
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

    public void testGetLinesWithAtLeastNPoints(int n) throws Exception {
        MvcResult result = mockMvc.perform(get("/lines/{n}", n)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        ApiResponse<List<Point>> responsePoints = objectMapper.readValue(responseContent, ApiResponse.class);
        assertThat(responsePoints.getResponse().size()).isEqualTo(binomialCoefficient(5, n));
        assertThat(responsePoints.getTimestamp()).isNotNull();
    }

    /**
     * Calcola il coefficiente binomiale C(n, k) = n! / (k! * (n-k)!)
     * @param n Il numero totale di elementi
     * @param k Il numero di elementi in ogni combinazione
     * @return Il coefficiente binomiale C(n, k)
     */
    public static long binomialCoefficient(int n, int k) {
        if (k > n || n == 0 || k == 1) {
            return 0;
        }
        if (k == 0 || k == n) {
            return 1;
        }
        return (factorial(n) / (factorial(k) * factorial(n - k)));
    }


    /**
     * Calculates the factorial of a given number.
     * @param n the non-negative integer whose factorial is to be calculated
     * @return the factorial of the number
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }



}