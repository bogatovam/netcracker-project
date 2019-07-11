package com.netcracker.controller;

import com.netcracker.model.documents.Exercise;
import com.netcracker.services.api.DataDisplayService;
import com.netcracker.services.api.PlanningService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.netcracker.controller.ControllersPaths.ExerciseController.*;

@RestController
public class ExerciseController {
    @Autowired
    DataDisplayService dataDisplayService;

    @Autowired
    PlanningService planningService;

    @PostMapping(CREATE_EXERCISE)
    @ApiOperation(value = "Create exercise")
    public Exercise createExercise(
            @ApiParam(value = "Exercise")
            @RequestBody Exercise exercise,
            Authentication authentication) {
        return planningService.createExercise(exercise, authentication.getName());
    }

    @GetMapping(GET_ALL_EXERCISES)
    @ApiOperation(value = "Return all Exercise")
    public List<Exercise> getAllExercise() {
        return dataDisplayService.getAllExercise();
    }

    @GetMapping(GET_EXERCISE_BY_ID)
    @ApiOperation(value = "Return exercise by id")
    public Exercise getExerciseByUd(
            @ApiParam(value = "Exercises id")
            @PathVariable String id) {
        return dataDisplayService.getExercise(id);
    }

    @PutMapping(SET_NAME_BY_ID)
    @ApiOperation(value = "Set name to exercise by id")
    public String setExerciseName(
            @ApiParam(value = "Exercises id")
            @PathVariable String id,
            @ApiParam(value = "Exercises name")
            @RequestBody String name,
            Authentication authentication) {
        return planningService.setExerciseName(id, name, authentication.getName());
    }

    @PutMapping(SET_DESCRIPTION_BY_ID)
    @ApiOperation(value = "Set description to exercise by id")
    public String setDescriptionName(
            @ApiParam(value = "Exercises id")
            @PathVariable String id,
            @ApiParam(value = "Exercises description")
            @RequestBody String name,
            Authentication authentication) {
        return planningService.setExerciseName(id, name, authentication.getName());
    }

    @DeleteMapping(DELETE_EXERCISE_BY_ID)
    @ApiOperation(value = "Delete exercise by id")
    public Exercise deleteExercise(
            @ApiParam(value = "Exercises id")
            @PathVariable String id,
            Authentication authentication) {
        return planningService.deleteExercise(id, authentication.getName());
    }

    @GetMapping(GET_ALL_EXERCISES_LOAD)
    @ApiOperation(value = "")
    public String[] getExerciseLoad() {
        return Exercise.muscleLoad;
    }
}
