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

    @GetMapping(GET_ALL_NAMES_WORKOUTS_COMPLEXES)
    @ApiOperation(value = "Get all names of workouts complexes")
    public Map<String, String> getAllNamesWorkoutComplex( Authentication authentication){
        return dataDisplayService.getWorkoutComplexesNames(authentication.getName());
    }

    @GetMapping(GET_NAME_BY_ID)
    @ApiOperation(value = "Get name of workout complex by id")
    public String getNameWorkoutComplex(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            Authentication authentication){
        return dataDisplayService.getNameWorkoutComplex(id, authentication.getName());
    }

    @GetMapping(GET_WORKOUTS_BY_ID)
    @ApiOperation(value = "Get workout complex workouts")
    public List<Workout> getWorkouts(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            Authentication authentication){
        return dataDisplayService.getWorkouts(id,authentication.getName());
    }

    @GetMapping(GET_NAMES_OF_WORKOUTS_BY_ID)
    @ApiOperation(value = "Get names of workouts id")
    public Map<String, String> getWorkoutsNames(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            Authentication authentication){
        return dataDisplayService.getWorkoutsNames(id,authentication.getName());
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
        return planningService.delWorkout(id, wid, authentication.getName());
    }

    @PutMapping(SET_NAME_WORKOUT_BY_ID)
    @ApiOperation(value = "Set workout name by workout complex id")
    public Workout setNameWorkout(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            @ApiParam(value = "Workout id")
            @PathVariable String wid,
            @ApiParam(value = "New workout name")
            @RequestBody String name,
            Authentication authentication){
        return planningService.setNameWorkout( wid, name, authentication.getName());
    }

    @PutMapping(SET_NAME_BY_ID)
    @ApiOperation(value = "Set workout complex name by id")
    public WorkoutComplex setNameWorkoutComplex(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            @ApiParam(value = "New workout complex name")
            @RequestBody String name,
            Authentication authentication){
        return planningService.setNameWorkoutComplex(id, name, authentication.getName());
    }
    @PutMapping(SET_DESCRIPTION_BY_ID)
    @ApiOperation(value = "Set workout complex description by id")
    public WorkoutComplex setDescriptionWorkoutComplex(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            @ApiParam(value = "New workout complex description")
            @RequestBody String description,
            Authentication authentication){
        return planningService.setDescriptionWorkoutComplex(id, description, authentication.getName());
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
