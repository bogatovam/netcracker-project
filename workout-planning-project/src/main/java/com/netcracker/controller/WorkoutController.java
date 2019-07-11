package com.netcracker.controller;

import com.netcracker.model.documents.Exercise;
import com.netcracker.model.documents.Workout;
import com.netcracker.model.documents.WorkoutComplex;
import com.netcracker.services.api.DataDisplayService;
import com.netcracker.services.api.PlanningService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.netcracker.controller.ControllersPaths.WorkoutController.*;

@RestController
public class WorkoutController {
    @Autowired
    DataDisplayService dataDisplayService;

    @Autowired
    PlanningService planningService;

    @PostMapping(CREATE_WORKOUT_WITH_COMPLEX)
    @ApiOperation(value = "Create workout with complex")
    public Workout createWorkoutWithWorkoutComplex(
            @ApiParam(value = "Workout complex id")
            @PathVariable String wcid,
            @ApiParam(value = "Body of created workout")
            @RequestBody Workout workout,
            Authentication authentication) {
        return planningService.createWorkout(wcid, workout, authentication.getName());
    }

    @PostMapping(UPDATE_WORKOUT)
    @ApiOperation(value = "Update workout with complex")
    public Workout updateWorkout(
            @ApiParam(value = "Body of updated workout")
            @RequestBody Workout workout,
            Authentication authentication) {
        return planningService.updateWorkout(workout, authentication.getName());
    }

    @GetMapping(GET_WORKOUT_BY_ID)
    @ApiOperation(value = "Get workout by id")
    public ResponseEntity<?> getWorkoutById(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            Authentication authentication) {
        Workout workoutById = dataDisplayService.getWorkoutById(id, authentication.getName());
        if (workoutById == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Workout  with id: " + id + " doesn't exist");
        return ResponseEntity.ok(workoutById);
    }

    @GetMapping(GET_ALL_WORKOUT)
    @ApiOperation(value = "Get all workout")
    public ResponseEntity<?> getAllWorkout(Authentication authentication) {
        return dataDisplayService.getAllWorkout(authentication.getName());
    }

    @GetMapping(GET_SOURCE_WORKOUT_COMPLEX_BY_ID)
    @ApiOperation(value = "Get source workout complex")
    public WorkoutComplex getSourceWorkoutComplex(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            Authentication authentication) {
        return dataDisplayService.getSourceWorkoutComplex(id, authentication.getName());
    }

    @GetMapping(CHANGE_SOURCE_WORKOUT_COMPLEX)
    @ApiOperation(value = "Change source workout complex of workout")
    public Workout changeSourceWorkoutComplex(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            @ApiParam(value = "Workout complex id")
            @PathVariable String oldwcid,
            @ApiParam(value = "Workout complex id")
            @PathVariable String newwcid,
            Authentication authentication) {
        return planningService.changeSourceWorkoutComplex(id, oldwcid, newwcid, authentication.getName());
    }

    @GetMapping(GET_EXERCISES_BY_ID)
    @ApiOperation(value = "Get all exercises")
    public List<Exercise> getExercises(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            Authentication authentication) {
        return dataDisplayService.getExercises(id, authentication.getName());
    }

    @DeleteMapping(DELETE_WORKOUT_BY_ID)
    @ApiOperation(value = "Delete workout by id")
    public Workout deleteWorkout(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            Authentication authentication) {
        return planningService.deleteWorkout(id, authentication.getName());
    }
}
