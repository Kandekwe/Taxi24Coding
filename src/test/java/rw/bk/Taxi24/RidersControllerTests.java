
package rw.bk.Taxi24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Test;


import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


public class RidersControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

   @Test
    public void whenFetchAll_ReturnJsonArray() throws Exception {
        String jsonResponse = "{\n"
                + "  \"responseCode\": \"00\",\n"
                + "  \"responseDescription\": \"Operation Successful\",\n"
                + "  \"entity\": [\n"
                + "    {\n"
                + "      \"id\": 1,\n"
                + "      \"firstName\": \"Guy\",\n"
                + "      \"lastName\": \"KANDEKWE\",\n"
                + "      \"phoneNumber\": \"0788752244\",\n"
                + "      \"riderAddress\": \"Hotel des milles collines\",\n"
                + "      \"email\": \"gkandekwe@gmail.com\",\n"
                + "      \"dateRegistered\": \"2020-05-12T08:03:14.000+0000\",\n"
                + "      \"status\": \"A\",\n"
                + "      \"lastKnownCoords\": \"-1.944960,30.062040\"\n"
                + "    }\n"
                + "  ]\n"
                + "}";

             RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/riders/v1/fetchall")
                .content(jsonResponse)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);


    }

}

