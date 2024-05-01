package synopsarapi.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LLMService {

    public String getSummaryFromText(String text) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("http://llm:5001/summarize");
        System.out.println("Calling " + url);
        //content-type: application/json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(text, headers);
        String response = restTemplate.postForObject(url, entity, String.class);
        return response;
    }
}
