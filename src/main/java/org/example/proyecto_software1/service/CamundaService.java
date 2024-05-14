package org.example.proyecto_software1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("singleton")
@RequiredArgsConstructor
public class CamundaService {

    private final RestTemplate restTemplate;
    private final String camundaBaseUrl = "http://localhost:8080/engine-rest";
    public String processId;

    public String startProcess(String processDefinitionKey, Map<String, Object> variables) {
        String url = camundaBaseUrl + "/process-definition/key/" + processDefinitionKey + "/start";
        Map<String, Object> request = new HashMap<>();
        request.put("variables", variables);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(response.getBody(), Map.class);
            processId = (String) responseMap.get("id");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.getBody();
    }
    public boolean getProcessVariable(String processId, String variableName) {
        String url = camundaBaseUrl + "/process-instance/" + processId + "/variables/" + variableName;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(response.getBody(), Map.class);
            return (boolean) responseMap.get("value");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getTaskId(String processInstanceId) {
        String url = camundaBaseUrl + "/task?processInstanceId=" + processInstanceId;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> tasks = objectMapper.readValue(response.getBody(), List.class);
            if (!tasks.isEmpty()) {
                return (String) tasks.get(0).get("id"); // Devolver el ID de la primera tarea
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void completeTask(String taskId) {
        String url = camundaBaseUrl + "/task/" + taskId + "/complete";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        // Agrega variables adicionales si es necesario

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        restTemplate.postForEntity(url, request, String.class);
    }
    public void completeTask(String taskId,Map<String, Object> variables) {
        String url = camundaBaseUrl + "/task/" + taskId + "/complete";
        Map<String, Object> request = new HashMap<>();
        request.put("variables", variables);
        restTemplate.postForEntity(url, request, String.class);
    }
}
