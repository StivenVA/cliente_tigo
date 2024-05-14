package org.example.proyecto_software1.controller.views;

import lombok.RequiredArgsConstructor;
import org.example.proyecto_software1.service.CamundaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Map;

@Controller
@RequestMapping("/Discover")
@RequiredArgsConstructor
public class DiscoverController {

    private final CamundaService camundaService;

    @PostMapping("/start")
    public String startProcess(@RequestBody Map<String, Object> params, Model model) {
        String processDefinitionKey = "Proceso_Servicios";

        System.out.println(params); // Replace with your actual key
        String response = camundaService.startProcess(processDefinitionKey, params);
        model.addAttribute("response", response);
        return "/discover";
    }
}
