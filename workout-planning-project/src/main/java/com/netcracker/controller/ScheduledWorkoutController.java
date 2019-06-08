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
    @ApiOperation(value = "Create scheduled workout")
    public ScheduledWorkout createScheduledWorkout(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            @ApiParam(value = "Scheduled workout body")
            @RequestBody ScheduledWorkout scheduledWorkout,
             Authentication authentication) {
        return planningService.createScheduledWorkout(id, scheduledWorkout, authentication.getName());
    }

    @GetMapping(GET_SCHEDULED_WORKOUT_BY_ID)
    @ApiOperation(value = "Get scheduled workout by id")
    public ScheduledWorkout getScheduledWorkout(
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String id,
            Authentication authentication) {
        return dataDisplayService.getScheduledWorkout(id, authentication.getName());
    }

    @GetMapping(GET_SOURCE_WORKOUT_BY_ID)
    @ApiOperation(value = "Get source workout by scheduled workout id")
    public Workout getSourceScheduledWorkout(
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String id,
             Authentication authentication) {
        return dataDisplayService.getSourceWorkout(id, authentication.getName());
    }

    @GetMapping(GET_ALL_SCHEDULED_WORKOUTS)
    @ApiOperation(value = "Get all scheduled workouts (for current user)")
    public List<ScheduledWorkout> getAllScheduledWorkouts(Authentication authentication) {
        return dataDisplayService.getAllScheduledWorkouts((String)authentication.getPrincipal());
    }

    @GetMapping(GET_SCHEDULED_DATE_BY_ID)
    @ApiOperation(value = "Get scheduled workout by date")
    public Date getDateScheduledWorkout(
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String id,
             Authentication authentication) {
        return dataDisplayService.getDateScheduledWorkout(id, authentication.getName());
    }

    @GetMapping(GET_STATUS_BY_ID)
    @ApiOperation(value = "Get status of scheduled workout")
    public String getStatusScheduledWorkout(
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String id,
             Authentication authentication) {
        return dataDisplayService.getStatusScheduledWorkout(id, authentication.getName());
    }

    @GetMapping(GET_ALL_CURRENT_EXERCISES_BY_ID)
    @ApiOperation(value = "Get all current exercise by scheduled workout id")
    public List<Exercise> getAllCurrentExercises(
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String id,
             Authentication authentication) {
        return dataDisplayService.getAllCurrentExercises(id, authentication.getName());
    }

    @GetMapping(GET_EXERCISES_MEASUREMENTS_BY_ID)
    @ApiOperation(value = "Get exercise measurements by scheduled workout id")
    public Map<Exercise, MeasurementsOfExercise> getExercisesMeasurements(
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String id,
             Authentication authentication) {
        return dataDisplayService.getExercisesMeasurements(id, authentication.getName());
    }

    @GetMapping(GET_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "Get exercise measurement by scheduled workout and exercise")
    public Map<Exercise, MeasurementsOfExercise> getExerciseMeasurement(
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String id,
            @ApiParam(value = "Exercise id")
            @PathVariable String eid,
             Authentication authentication) {
        return dataDisplayService.getExercisesMeasurements(id, authentication.getName());
    }

    @GetMapping(GET_INFORMATION_BY_ID)
    @ApiOperation(value = "Get information about scheduled workout by id")
    public Map<String, String> getInformation(
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String id,
             Authentication authentication) {
        return dataDisplayService.getScheduledWorkoutInformation(id, authentication.getName());
    }

    @PutMapping(SET_STATUS_BY_ID)
    @ApiOperation(value = "Set status of scheduled workout by id")
    public void setStatus(
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String id,
            @ApiParam(value = "Scheduled workout status")
            @RequestBody String status,
             Authentication authentication) {
        planningService.setStatusScheduledWorkout(id, status, authentication.getName());
    }

    @PostMapping(ADD_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "Add exercise measurement by sheduled workout id and exercise id")
    public MeasurementsOfExercise addExerciseMeasurement(
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String id,
            @ApiParam(value = "Exercise id")
            @PathVariable String eid,
            @ApiParam(value = "Measurement body")
            @RequestBody Measurement measurement,
             Authentication authentication) {
        return planningService.addExerciseMeasurement(id, eid, measurement, authentication.getName());
    }

    @PutMapping(UPDATE_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "Update exercise measurement by scheduled workout id and exercise id")
    public MeasurementsOfExercise updateExerciseMeasurement(
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String id,
            @ApiParam(value = "Exercise id")
            @PathVariable String eid,
            @ApiParam(value = "Number of updated measurement")
            @PathVariable String num,
            @ApiParam(value = "New measurement body")
            @RequestBody Measurement measurement,
             Authentication authentication) {
        return planningService.updateExerciseMeasurement(id, eid, num, measurement, authentication.getName());
    }

    @DeleteMapping(DEL_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "Delete exercise measurement by id")
    public MeasurementsOfExercise delExerciseMeasurement(
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String id,
            @ApiParam(value = "Exercise  id")
            @PathVariable String eid,
            @ApiParam(value = "Number of deleted measurement")
            @PathVariable String num,
             Authentication authentication) {
        return planningService.delExerciseMeasurement(id, eid, num, authentication.getName());
    }

    @DeleteMapping(DELETE_SCHEDULED_WORKOUT_BY_ID)
    @ApiOperation(value = "Delete scheduled workout by id")
    public ScheduledWorkout deleteScheduledWorkout(
            @ApiParam(value = "Source Workout id")
            @PathVariable String id,
            @ApiParam(value = "Scheduled workout id")
            @PathVariable String swid,
             Authentication authentication) {
        return planningService.deleteScheduledWorkout(id, swid, authentication.getName());
    }
}
