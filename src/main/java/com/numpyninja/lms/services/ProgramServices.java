package com.numpyninja.lms.services;

import com.numpyninja.lms.entity.Program;
import com.numpyninja.lms.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProgramServices {
    @Autowired
    private ProgramRepository programRepository;

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public List<Program> getAllPrograms(String searchString) {
        return programRepository.findByProgramNameContainingIgnoreCaseOrderByProgramIdAsc(searchString);
    }

    public Optional<Program> findProgram(Long id) {
        return programRepository.findById(id);
    }

    public Program createProgram(Program newProgram) {
        return programRepository.saveAndFlush(newProgram);
    }

    public Program updateProgram(Program updatedProgram) {
        return programRepository.save(updatedProgram);
    }

    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }

}
