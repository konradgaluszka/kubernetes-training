package training;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import training.jobs.Job;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void persistsAndListsJobs() throws Exception {
        //expect
        mockMvc.perform(MockMvcRequestBuilders.get("/jobs")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(jsonPath("$", hasSize(0)));

        //when
        mockMvc.perform(MockMvcRequestBuilders
                    .post("/jobs")
                    .contentType("application/json")
                    .content(job("job-1")))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/jobs")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(jsonPath("$", hasSize(1)));

    }

    private String job(String name) throws JsonProcessingException {
        return objectMapper.writeValueAsString(new Job(name));
    }

}