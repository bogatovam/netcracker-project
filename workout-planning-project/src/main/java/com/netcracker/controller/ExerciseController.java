package com.netcracker.controller;

import com.netcracker.model.documents.Exercise;
import com.netcracker.services.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    @Autowired
    DemoService demoService;

    @GetMapping("/all")
    public List<Exercise> getAllExercise() {
        return demoService.getAllExercise();
    }

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable String id){
        return demoService.getExercise(id);
    }
}
