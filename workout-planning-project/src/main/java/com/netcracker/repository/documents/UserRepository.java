package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.User;

public interface UserRepository extends ArangoRepository<User, String> {
    User findByLogin(String login);
    Boolean existsByLogin(String username);
    Boolean existsByEmail(String email);

}
