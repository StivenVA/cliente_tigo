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
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private CamundaService camundaService;

    @PostMapping("/submitTerms")
    public String submitTerms(@RequestParam("acceptTerms") String acceptTerms, Model model) {
        String processDefinitionKey = "Proceso_Servicios";
        Map<String, Object> variables = new HashMap<>();
        Map<String, Object> enumVariable = new HashMap<>();
        enumVariable.put("value", acceptTerms);
        enumVariable.put("type", "String"); // Usar "String" si el tipo enum está basado en una cadena
        variables.put("contrato", enumVariable);
        var actualTaskid = camundaService.getTaskId(camundaService.processId);
        camundaService.completeTask(actualTaskid, variables);
        if(!acceptTerms.equals("SI")) {
            return "redirect:/reject";
        }
        return "redirect:/success";
    }
}
