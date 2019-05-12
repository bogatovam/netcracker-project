package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.UserToWComplex;

import java.util.List;

public interface UserToWComplexRepository extends ArangoRepository<UserToWComplex, String> {
    List<UserToWComplex> removeAllByWcomplexId(String id);
}
