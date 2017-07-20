package org.ameet.kafkasample.repository;

import org.ameet.kafkasample.model.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ameet.chaubal on 7/20/2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
