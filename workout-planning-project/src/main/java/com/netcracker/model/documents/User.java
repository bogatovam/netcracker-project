package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Relations;
import com.netcracker.model.edges.UserToWComplex;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.netcracker.model.CollectionsNames.USER;

@Data
@Document(USER)
public class User {

    @Id
    private String id;

    private String login;
    private String email;
    private String password;

    @Field("full-name")
    private String fullName;

    private String gender;
    private Integer age;
    private Integer weight;
    private Integer growth;

    @Field("workouts_goal")
    private Goals workoutsGoal;

    @Relations(edges = UserToWComplex.class, direction = Relations.Direction.OUTBOUND)
    private List<WorkoutComplex> workoutsComplexes;

    public static enum Goals {
        WEIGHT_LOSS,
        SET_MASS,
        MUSCLE_RELIEF
    }
}
