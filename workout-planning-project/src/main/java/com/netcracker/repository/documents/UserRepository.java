package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.User;

public interface UserRepository extends ArangoRepository<User, String> {
}
