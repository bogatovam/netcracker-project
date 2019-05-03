package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.UserToWComplex;

public interface UserToWComplexRepository extends ArangoRepository<UserToWComplex, String> {
}
