package com.olo.discoveryservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "management.endpoint.health.show-details=always",
        "spring.security.user.name=testuser",
        "spring.security.user.password=testpass",
        "eureka.client.register-with-eureka=false",
        "eureka.client.fetch-registry=false"
})
@AutoConfigureMockMvc
public class HealthCheckTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test to verify the Actuator health endpoint is available and reports UP status.
     * @throws Exception if the request simulation fails
     */
    @Test
    void healthCheckShouldReturnUpStatus() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

}
