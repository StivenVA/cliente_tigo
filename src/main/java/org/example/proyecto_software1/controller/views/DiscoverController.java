package org.example.proyecto_software1.controller.views;

import org.example.proyecto_software1.service.CamundaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/discover")
public class DiscoverController {

    @Autowired
    private CamundaService camundaService;

    @PostMapping("/submitForm")
    public String submitForm(@RequestParam("customerName") String name,
                             @RequestParam("customerAddress") String address,
                             @RequestParam("customerNumber") String number, Model model) {
        String processDefinitionKey = "Proceso_Servicios";
        Map<String, Object> variables = new HashMap<>();
        variables.put("nombreForm", Map.of("value", name, "type", "String"));
        variables.put("direccionForm", Map.of("value", address, "type", "String"));
        variables.put("numeroForm", Map.of("value", number, "type", "String"));

        camundaService.startProcess(processDefinitionKey, variables);

        boolean disponibilidad = camundaService.getProcessVariable(camundaService.processId, "disponibilidad");

        if (disponibilidad) {
            model.addAttribute("processId", camundaService.processId);
            return "redirect:/plans";
        } else {
            return "redirect:/notAvailable";
        }
    }
    @PostMapping("/completeActivity")
    public String completeActivity() {
        String taskId = camundaService.getTaskId(camundaService.processId);
        if (taskId != null) {
            camundaService.completeTask(taskId);
        }
        return "redirect:/";
    }
}
