package com.netcracker.controller;

import com.netcracker.model.documents.Exercise;
import com.netcracker.services.api.DemoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.netcracker.controller.ControllersPaths.ExerciseController.*;

@RestController
public class ExerciseController {
    @Autowired
    DemoService demoService;


    @GetMapping(GET_ALL_EXERCISES)
    @ApiOperation(value = "Show all Exercise")
    public List<Exercise> getAllExercise() {
        return demoService.getAllExercise();
    }

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable String id){
        return demoService.getExercise(id);
    }
}
