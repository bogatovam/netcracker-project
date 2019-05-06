package com.netcracker.controller;

import com.netcracker.model.documents.WorkoutComplex;
import com.netcracker.services.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workoutcomplex")
public class WorkoutComplexController {
    @Autowired
    DemoService demoService;

    @GetMapping("user/{id}")
    public List<WorkoutComplex> getAllByUserId(
            @PathVariable String id) {
        return demoService.getAllWorkoutComplexByUser(id);
    }

    @PostMapping("user/{id}")
    public WorkoutComplex createWorkoutComplex(
            @PathVariable String id,
            @RequestBody WorkoutComplex complex) {
        return  demoService.createWorkoutComplex(id, complex);
    }

}
