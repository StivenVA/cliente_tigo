package org.example.proyecto_software1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CamundaService {

    private final RestTemplate restTemplate;
    private final String camundaBaseUrl = "http://localhost:8080/engine-rest";

    public String startProcess(String processDefinitionKey, Map<String, Object> variables) {
        String url = camundaBaseUrl + "/process-definition/key/" + processDefinitionKey + "/start";
        Map<String, Object> request = new HashMap<>();
        request.put("variables", variables);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();
    }
}
