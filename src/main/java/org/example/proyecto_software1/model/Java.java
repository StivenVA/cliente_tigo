package org.example.proyecto_software1.model;

import java.util.Map;

public class StartProcessDTO {
    private Map<String, Variable> variables;

    public StartProcessDTO() {
    }

    public Map<String, Variable> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Variable> variables) {
        this.variables = variables;
    }
}
