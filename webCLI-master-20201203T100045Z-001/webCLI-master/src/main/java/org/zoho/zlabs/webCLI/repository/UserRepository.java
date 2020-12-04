package org.zoho.zlabs.webCLI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zoho.zlabs.webCLI.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
