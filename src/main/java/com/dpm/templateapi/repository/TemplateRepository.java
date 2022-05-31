package com.dpm.templateapi.repository;

import com.dpm.templateapi.datamodel.Template;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TemplateRepository extends CrudRepository<Template, Integer> {
    List<Template> findAllByUsername(String Username);
    Template findByUsername(String Username);
}
