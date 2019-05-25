package com.netcracker.controller;

import com.netcracker.model.documents.*;
import com.netcracker.security.details.UserPrincipal;
import com.netcracker.services.api.DataDisplayService;
import com.netcracker.services.api.PlanningService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.netcracker.controller.ControllersPaths.ScheduledWorkoutController.*;

@RestController
public class ScheduledWorkoutController {
    @Autowired
    DataDisplayService dataDisplayService;

    @Autowired
    PlanningService planningService;

    @PostMapping(CREATE_SCHEDULED_WORKOUT)
    @ApiOperation(value = "")
    public ScheduledWorkout createScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody ScheduledWorkout scheduledWorkout,
             Authentication authentication) {
        return planningService.createScheduledWorkout(id, scheduledWorkout, authentication.getName());
    }

    @GetMapping(GET_SCHEDULED_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public ScheduledWorkout getScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
            Authentication authentication) {
        return dataDisplayService.getScheduledWorkout(id, authentication.getName());
    }

    @GetMapping(GET_SOURCE_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public Workout getSourceScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
             Authentication authentication) {
        return dataDisplayService.getSourceWorkout(id, authentication.getName());
    }

    @GetMapping(GET_ALL_SCHEDULED_WORKOUTS)
    @ApiOperation(value = "")
    public List<ScheduledWorkout> getAllScheduledWorkouts(Authentication authentication) {
        return dataDisplayService.getAllScheduledWorkouts((String)authentication.getPrincipal());
    }

    @GetMapping(GET_SCHEDULED_DATE_BY_ID)
    @ApiOperation(value = "")
    public Date getDateScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
             Authentication authentication) {
        return dataDisplayService.getDateScheduledWorkout(id, authentication.getName());
    }

    @GetMapping(GET_STATUS_BY_ID)
    @ApiOperation(value = "")
    public String getStatusScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
             Authentication authentication) {
        return dataDisplayService.getStatusScheduledWorkout(id, authentication.getName());
    }

    @GetMapping(GET_ALL_CURRENT_EXERCISES_BY_ID)
    @ApiOperation(value = "")
    public List<Exercise> getAllCurrentExercises(
            @ApiParam(value = "")
            @PathVariable String id,
             Authentication authentication) {
        return dataDisplayService.getAllCurrentExercises(id, authentication.getName());
    }

    @GetMapping(GET_EXERCISES_MEASUREMENTS_BY_ID)
    @ApiOperation(value = "")
    public Map<Exercise, MeasurementsOfExercise> getExercisesMeasurements(
            @ApiParam(value = "")
            @PathVariable String id,
             Authentication authentication) {
        return dataDisplayService.getExercisesMeasurements(id, authentication.getName());
    }

    @GetMapping(GET_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "")
    public Map<Exercise, MeasurementsOfExercise> getExerciseMeasurement(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid,
             Authentication authentication) {
        return dataDisplayService.getExercisesMeasurements(id, authentication.getName());
    }

    @GetMapping(GET_INFORMATION_BY_ID)
    @ApiOperation(value = "")
    public Map<String, String> getInformation(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid,
             Authentication authentication) {
        return dataDisplayService.getScheduledWorkoutInformation(id, authentication.getName());
    }

    @PostMapping(SET_STATUS_BY_ID)
    @ApiOperation(value = "")
    public void setStatus(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody String status,
             Authentication authentication) {
        planningService.setStatusScheduledWorkout(id, status, authentication.getName());
    }

    @PostMapping(ADD_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "")
    public MeasurementsOfExercise addExerciseMeasurement(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid,
            @ApiParam(value = "")
            @RequestBody Measurement measurement,
             Authentication authentication) {
        return planningService.addExerciseMeasurement(id, eid, measurement, authentication.getName());
    }

    @PostMapping(UPDATE_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "")
    public MeasurementsOfExercise updateExerciseMeasurement(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid,
            @ApiParam(value = "")
            @PathVariable String num,
            @ApiParam(value = "")
            @RequestBody Measurement measurement,
             Authentication authentication) {
        return planningService.updateExerciseMeasurement(id, eid, num, measurement, authentication.getName());
    }

    @GetMapping(DEL_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "")
    public MeasurementsOfExercise delExerciseMeasurement(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid,
            @ApiParam(value = "")
            @PathVariable String num,
             Authentication authentication) {
        return planningService.delExerciseMeasurement(id, eid, num, authentication.getName());
    }

    @GetMapping(DELETE_SCHEDULED_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public ScheduledWorkout deleteScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String swid,
             Authentication authentication) {
        return planningService.deleteScheduledWorkout(id, swid, authentication.getName());
    }
}
