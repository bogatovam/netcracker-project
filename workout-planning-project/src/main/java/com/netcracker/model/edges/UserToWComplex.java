package com.netcracker.model.edges;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.User;
import com.netcracker.model.documents.WorkoutComplex;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.USER_TO_WORKOUT_COMPLEX;

@Data
@Edge(USER_TO_WORKOUT_COMPLEX)
public class UserToWComplex {
    @Id
    private String id;

    @From
    private User user;

    @To
    private WorkoutComplex wcomplex;
}
