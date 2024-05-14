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
    @GetMapping("/success")
    public String showSuccess() {
        return "success";
    }
    @GetMapping("/reject")
    public String showReject() {
        return "reject";
    }
    @GetMapping("/contract")
    public String showContract() {
        return "contract";
    }
    @GetMapping("/notAvailable")
    public String showNoDisponibilidad() {
        return "notAvailable";
    }
        @GetMapping("/plans")
        public String showPlans() {
            return "plans";
        }
}


