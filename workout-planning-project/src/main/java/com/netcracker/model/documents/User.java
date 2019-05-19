package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Relations;
import com.netcracker.model.edges.UserToWComplex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.netcracker.model.CollectionsNames.USER;

@Data
@Document(USER)
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Application user")
public class User {
    public static final String DEFAULT_USER_ID = "";

    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    private String login;
    private String email;
    private String password;

    @Field("full-name")
    private String fullName;

    private String roles = "";
    private String permissions = "";
    private State state;

    private String gender;
    private Integer age;
    private Integer weight;
    private Integer growth;

    @Field("workouts_goal")
    private Goals workoutsGoal;

    @Relations(edges = UserToWComplex.class, direction = Relations.Direction.OUTBOUND)
    private List<WorkoutComplex> workoutsComplexes;

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
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
