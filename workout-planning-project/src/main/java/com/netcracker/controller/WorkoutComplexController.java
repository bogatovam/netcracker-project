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
import java.util.Map;

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
            @ApiParam(value = "")
            @RequestBody WorkoutComplex workoutComplex){
        return planningService.createWorkoutComplex(workoutComplex);
    }

    @GetMapping(GET_ALL_WORKOUTS_COMPLEXES)
    @ApiOperation(value = "")
    public List<WorkoutComplex> getAllWorkoutComplex( Authentication authentication){
        return dataDisplayService.getAllWorkoutComplex(authentication.getName());
    }

    @GetMapping(GET_ALL_NAMES_WORKOUTS_COMPLEXES)
    @ApiOperation(value = "")
    public Map<String, String> getAllNamesWorkoutComplex( Authentication authentication){
        return dataDisplayService.getWorkoutComplexesNames(authentication.getName());
    }

    @GetMapping(GET_NAME_BY_ID)
    @ApiOperation(value = "")
    public String getNameWorkoutComplex(
            @ApiParam(value = "")
            @PathVariable String id,
            Authentication authentication){
        return dataDisplayService.getNameWorkoutComplex(id, authentication.getName());
    }

    @GetMapping(GET_WORKOUTS_BY_ID)
    @ApiOperation(value = "")
    public List<Workout> getWorkouts(
            @ApiParam(value = "")
            @PathVariable String id,
            Authentication authentication){
        return dataDisplayService.getWorkouts(id,authentication.getName());
    }

    @GetMapping(GET_NAMES_OF_WORKOUTS_BY_ID)
    @ApiOperation(value = "")
    public Map<String, String> getWorkoutsNames(
            @ApiParam(value = "")
            @PathVariable String id,
            Authentication authentication){
        return dataDisplayService.getWorkoutsNames(id,authentication.getName());
    }

    @PostMapping(ADD_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public Workout addWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody Workout workout,
            Authentication authentication){
        return planningService.addWorkout(id, workout);
    }

    @GetMapping(DEL_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public Workout delWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String wid,
            Authentication authentication){
        return planningService.delWorkout(id, wid);
    }

    @PostMapping(SET_NAME_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public Workout setNameWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String wid,
            @ApiParam(value = "")
            @RequestBody String name,
            Authentication authentication){
        return planningService.setNameWorkout( wid, name);
    }

    @PostMapping(SET_NAME_BY_ID)
    @ApiOperation(value = "")
    public WorkoutComplex setNameWorkoutComplex(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody String name,
            Authentication authentication){
        return planningService.setNameWorkoutComplex(id, name);
    }

    @GetMapping(DELETE_WORKOUT_COMPLEX_BY_ID)
    @ApiOperation(value = "")
    public WorkoutComplex setNameWorkoutComplex(
            @ApiParam(value = "")
            @PathVariable String id,
            Authentication authentication){
        return planningService.deleteWorkoutComplex(id);
    }


}
