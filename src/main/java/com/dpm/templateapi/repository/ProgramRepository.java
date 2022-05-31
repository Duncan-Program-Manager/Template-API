package com.dpm.templateapi.repository;

import com.dpm.templateapi.datamodel.Program;
import org.springframework.data.repository.CrudRepository;

public interface ProgramRepository extends CrudRepository<Program, String> {
}
