package org.hisp.metadata.repositories;

import org.hisp.metadata.api.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zubair on 07.02.17.
 */
public interface UserRepository extends CrudRepository<User, Integer>
{
    User getUserByEmail( String email );
}
