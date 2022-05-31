package com.dpm.templateapi.controller;

import com.dpm.templateapi.datamodel.Program;
import com.dpm.templateapi.datamodel.Template;
import com.dpm.templateapi.dto.TemplateDTO;
import com.dpm.templateapi.endpoint.TemplateEndpoints;
import com.dpm.templateapi.repository.ProgramRepository;
import com.dpm.templateapi.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = TemplateEndpoints.BASE)
public class TemplateController {

    private final TemplateRepository templateRepository;
    private final ProgramRepository programRepository;

    @Autowired
    public TemplateController(TemplateRepository templateRepository, ProgramRepository programRepository)
    {
        this.templateRepository = templateRepository;
        this.programRepository = programRepository;
    }

    @GetMapping(value = TemplateEndpoints.GETTEMPLATE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTemplate(@RequestParam TemplateDTO dto)
    {
        List<Template> templates = templateRepository.findAllByUsername(dto.getUsername());
        Template wantedTemplate = null;
        for (Template template: templates) {
            if(template.getName() == dto.getName())
            {
                wantedTemplate = template;
            }
        }
        return new ResponseEntity<>(wantedTemplate, HttpStatus.OK);
    }

    @GetMapping(value = TemplateEndpoints.GETALLTEMPLATES, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUserTemplates(@RequestParam String Username)
    {
        return new ResponseEntity<>(templateRepository.findAllByUsername(Username), HttpStatus.OK);
    }

    @PostMapping(value = TemplateEndpoints.CREATETEMPLATE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTemplate(@RequestBody TemplateDTO dto)
    {
        for (String programName: dto.getPrograms()) {
            Program program = new Program();
            program.setName(programName);
            if(programRepository.findById(programName).isEmpty()) {
                programRepository.save(program);
            }
        }
        List<Program> programList = (List<Program>) programRepository.findAllById(dto.getPrograms());
        Template template = new Template();
        template.setName(dto.getName());
        template.setUsername(dto.getUsername());
        template.setPrograms(programList);
        templateRepository.save(template);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = TemplateEndpoints.UPDATETEMPLATE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTemplate(@RequestBody TemplateDTO dto)
    {
        for (String programName: dto.getPrograms()) {
            Program program = new Program();
            program.setName(programName);
            if(programRepository.findById(programName).isEmpty()) {
                programRepository.save(program);
            }
        }
        List<Program> programList = (List<Program>) programRepository.findAllById(dto.getPrograms());
        Template template = new Template();
        template.setName(dto.getName());
        template.setUsername(dto.getUsername());
        template.setPrograms(programList);
        templateRepository.save(template);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = TemplateEndpoints.DELETETEMPLATE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTemplate(@RequestBody TemplateDTO dto)
    {
        List<Template> templates = templateRepository.findAllByUsername(dto.getUsername());
        Template wantedTemplate = null;
        for (Template template: templates) {
            if(template.getName() == dto.getName())
            {
                wantedTemplate = template;
            }
        }
        templateRepository.delete(wantedTemplate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
