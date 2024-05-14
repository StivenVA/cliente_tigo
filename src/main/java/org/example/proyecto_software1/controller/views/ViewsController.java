package org.example.proyecto_software1.controller.views;

import org.example.proyecto_software1.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewsController {

    @GetMapping("/discover")
    public String showForm() {
        return "discover";
    }

    @PostMapping("/submitForm")
    public String submitForm(@RequestBody Client client, Model model) {
        System.out.println(client);

        return "plans";
    }

        @GetMapping("/plans")
        public String showPlans() {
            return "plans";
        }

        @PostMapping("/submitPlan")
        public String submitPlan(@RequestParam("selectedPlan") String selectedPlan, Model model) {
            model.addAttribute("selectedPlan", selectedPlan);
            // TODO
            //ENVIAR PARA REGLA DE NEGOCIO
            return "confirmation";
        }


}


