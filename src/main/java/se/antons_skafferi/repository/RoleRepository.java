package se.antons_skafferi.repository;

import org.springframework.data.repository.CrudRepository;
import se.antons_skafferi.dataClass.Role;

import java.util.List;
import java.util.Optional;

/**
 * <h1>RoleRepository</h1>
 * This interface is used to communicate with the database regarding roles.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    /**
     * This method is used to find all {@code Role}.
     * @return {@code List<Role>} A list of all roles.
     */
    List<Role> findAll();
    /**
     * This method is used to find a role by id.
     * @param id The id of the role.
     * @return {@code Optional<Role>} Returns the role.
     */
    Optional<Role> findById(int id);
}