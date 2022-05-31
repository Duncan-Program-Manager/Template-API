package com.dpm.templateapi.dto;

import com.dpm.templateapi.datamodel.Program;

import java.util.List;

public class TemplateDTO {
    private String Name;
    private List<String> programs;
    private String Username;

    public TemplateDTO(String name, List<String> programs, String username) {
        Name = name;
        this.programs = programs;
        Username = username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getPrograms() {
        return programs;
    }

    public void setPrograms(List<String> programs) {
        this.programs = programs;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
