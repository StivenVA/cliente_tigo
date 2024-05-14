package org.example.proyecto_software1.controller.views;

import org.example.proyecto_software1.service.CamundaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/plans")
public class PlansController {
    @Autowired
    private CamundaService camundaService;

    @PostMapping("/submitPlan")
    public String submitPlan(@RequestParam("selectedPlan") String selectedPlan) {
        String processDefinitionKey = "Proceso_Servicios";
        Map<String, Object> variables = new HashMap<>();
        variables.put("plan", Map.of("value", selectedPlan, "type", "String"));
        var actualTaskid = camundaService.getTaskId(camundaService.processId);
        camundaService.completeTask(actualTaskid, variables);
        return "redirect:/contract";
    }
}
