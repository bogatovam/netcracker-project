package com.netcracker.controller;

import com.netcracker.model.documents.Workout;
import com.netcracker.model.documents.WorkoutComplex;
import com.netcracker.services.api.DataDisplayService;
import com.netcracker.services.api.PlanningService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.netcracker.controller.ControllersPaths.WorkoutComplexController.*;

@RestController
public class WorkoutComplexController {
    @Autowired
    DataDisplayService dataDisplayService;

    @Autowired
    PlanningService planningService;

    @PostMapping(CREATE_WORKOUT_COMPLEX)
    @ApiOperation(value = "Create workout complex")
    public WorkoutComplex createWorkoutComplex(
            @ApiParam(value = "Body of сreated workout complex")
            @RequestBody WorkoutComplex workoutComplex,
            Authentication authentication){
        return planningService.createWorkoutComplex(workoutComplex, authentication.getName());
    }

    @GetMapping(GET_ALL_WORKOUTS_COMPLEXES)
    @ApiOperation(value = "Get all workout complexes (for current user)")
    public List<WorkoutComplex> getAllWorkoutComplex( Authentication authentication){
        return dataDisplayService.getAllWorkoutComplex(authentication.getName());
    }

    @GetMapping(GET_WORKOUTS_BY_ID)
    @ApiOperation(value = "Get workout complex workouts")
    public List<Workout> getWorkouts(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            Authentication authentication){
        return dataDisplayService.getWorkouts(id,authentication.getName());
    }

    @PostMapping(ADD_WORKOUT_BY_ID)
    @ApiOperation(value = "Add workout to workout complex")
    public Workout addWorkout(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            @ApiParam(value = "Added workout")
            @RequestBody Workout workout,
            Authentication authentication){
        return planningService.addWorkout(id, workout, authentication.getName());
    }

    @DeleteMapping(DEL_WORKOUT_BY_ID)
    @ApiOperation(value = "Delete workout from workout complex")
    public Workout delWorkout(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            @ApiParam(value = "Deleted workout id")
            @PathVariable String wid,
            Authentication authentication){
        return planningService.deleteWorkout(id, wid, authentication.getName());
    }

    @DeleteMapping(DELETE_WORKOUT_COMPLEX_BY_ID)
    @ApiOperation(value = "Delete workout complex id")
    public WorkoutComplex deleteWorkoutComplex(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            Authentication authentication){
        return planningService.deleteWorkoutComplex(id, authentication.getName());
    }
}
