package com.netcracker.model.edges;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.User;
import com.netcracker.model.documents.WorkoutComplex;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.USER_TO_WORKOUT_COMPLEX;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Edge(USER_TO_WORKOUT_COMPLEX)
@ApiModel(description = "A helper class that describes the relationship between user and workout complex")
public class UserToWComplex {
    @Id
    private String id;

    @From
    private final User user;

    @To
    private final WorkoutComplex wcomplex;
}
