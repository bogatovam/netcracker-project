package com.netcracker.controller;

import com.netcracker.model.documents.*;
import com.netcracker.services.api.DataDisplayService;
import com.netcracker.services.api.PlanningService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestBody ScheduledWorkout scheduledWorkout) {
        return planningService.createScheduledWorkout(id, scheduledWorkout);
    }

    @GetMapping(GET_SCHEDULED_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public ScheduledWorkout getScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id) {
        return dataDisplayService.getScheduledWorkout(id);
    }

    @GetMapping(GET_SOURCE_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public Workout getSourceScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id) {
        return dataDisplayService.getSourceWorkout(id);
    }

    @GetMapping(GET_ALL_SCHEDULED_WORKOUTS)
    @ApiOperation(value = "")
    public List<ScheduledWorkout> getAllScheduledWorkouts() {
        return dataDisplayService.getAllScheduledWorkouts();
    }

    @GetMapping(GET_SCHEDULED_DATE_BY_ID)
    @ApiOperation(value = "")
    public Date getDateScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id) {
        return dataDisplayService.getDateScheduledWorkout(id);
    }

    @GetMapping(GET_STATUS_BY_ID)
    @ApiOperation(value = "")
    public String getStatusScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id) {
        return dataDisplayService.getStatusScheduledWorkout(id);
    }

    @GetMapping(GET_ALL_CURRENT_EXERCISES_BY_ID)
    @ApiOperation(value = "")
    public List<Exercise> getAllCurrentExercises(
            @ApiParam(value = "")
            @PathVariable String id) {
        return dataDisplayService.getAllCurrentExercises(id);
    }

    @GetMapping(GET_EXERCISES_MEASUREMENTS_BY_ID)
    @ApiOperation(value = "")
    public Map<Exercise, MeasurementsOfExercise> getExercisesMeasurements(
            @ApiParam(value = "")
            @PathVariable String id) {
        return dataDisplayService.getExercisesMeasurements(id);
    }

    @GetMapping(GET_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "")
    public Map<Exercise, MeasurementsOfExercise> getExerciseMeasurement(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid) {
        return dataDisplayService.getExercisesMeasurements(id);
    }

    @GetMapping(GET_INFORMATION_BY_ID)
    @ApiOperation(value = "")
    public Map<String, String> getInformation(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid) {
        return dataDisplayService.getScheduledWorkoutInformation(id);
    }

    @PostMapping(SET_STATUS_BY_ID)
    @ApiOperation(value = "")
    public void setStatus(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @RequestBody String status) {
        planningService.setStatusScheduledWorkout(id, status);
    }

    @PostMapping(ADD_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "")
    public MeasurementsOfExercise addExerciseMeasurement(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid,
            @ApiParam(value = "")
            @RequestBody Measurement measurement) {
        return planningService.addExerciseMeasurement(id, eid, measurement);
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
            @RequestBody Measurement measurement) {
        return planningService.updateExerciseMeasurement(id, eid, num, measurement);
    }

    @GetMapping(DEL_EXERCISE_MEASUREMENT_BY_ID)
    @ApiOperation(value = "")
    public MeasurementsOfExercise delExerciseMeasurement(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String eid,
            @ApiParam(value = "")
            @PathVariable String num) {
        return planningService.delExerciseMeasurement(id, eid, num);
    }

    @GetMapping(DELETE_SCHEDULED_WORKOUT_BY_ID)
    @ApiOperation(value = "")
    public ScheduledWorkout deleteScheduledWorkout(
            @ApiParam(value = "")
            @PathVariable String id,
            @ApiParam(value = "")
            @PathVariable String swid) {
        return planningService.deleteScheduledWorkout(id, swid);
    }
}
