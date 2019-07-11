package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Relations;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.model.edges.UserToWComplex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.netcracker.model.CollectionsNames.USER;

@Data
@Document(USER)
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Application user")
public class User {
    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    private String login;
    private String email;
    private String password;

    @Field("full-name")
    private String fullName;

    private String roles;
    private State state;

    private String gender;
    private Date dateOfBirth;
    private Integer weight;
    private Integer growth;

    @Field("workouts_goal")
    private Goals workoutsGoal;

    @Transient
    private String token;

    @JsonIgnore
    @Relations(edges = UserToWComplex.class, direction = Relations.Direction.OUTBOUND)
    private List<WorkoutComplex> workoutsComplexes;

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public boolean isValid() {
        return (login != null && login.length() > 6) &&
                (fullName != null && fullName.length() > 0) &&
                (email != null && email.length() > 6) &&
                (password != null && password.length() > 6) &&
                (roles != null) &&
                (state != null) &&
                (gender != null) &&
                (dateOfBirth != null) &&
                (weight != null);
    }

    public static enum Goals {
        WEIGHT_LOSS,
        SET_MASS,
        MUSCLE_RELIEF
    }

    public static enum State {
        ACTIVE, BANNED, DELETED;
    }
}
