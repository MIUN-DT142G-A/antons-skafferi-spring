package se.antons_skafferi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.antons_skafferi.dataClass.Employee;
import se.antons_skafferi.dataClass.Role;

import java.util.List;
import java.util.Optional;

/**
 * <h1>EmployeeRepository</h1>
 * This interface is used to communicate with the database regarding employees.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    /**
     * This method is used to find all {@code Employee}s.
     * @return {@code List<Employee>} A list of all employees.
     */
    List<Employee> findAll();

    /**
     * This method is used to find an employee by id.
     * @param id The id of the employee.
     * @return The employee.
     */
    @Query("SELECT e FROM Employee e WHERE e.employee_id = :id")
    Optional<Employee> findById(int id);

    /**
     * This method is used to find an {@code Employee} by {@code Name}.
     * @param name The name of the employee.
     * @return {@code List<Employee> }The employee.
     */
    @Query("SELECT e FROM Employee e WHERE e.person.first_name LIKE %:name% OR e.person.last_name LIKE %:name%")
    List<Employee> findByName(String name);

    /**
     * This method is used to find an employee password by email.
     * @param email The email of the employee.
     * @return {@code String} – The code string of the employee.
     */
    @Query("SELECT e.password FROM Employee e WHERE e.person.email = :email")
    String findPasswordByEmail(String email);

    /**
     * This method is used to find the role of an employee by their id.
     * @param employee_id The id of the employee.
     * @return {@code List<Role>} The role of the employee.
     */
    @Query("SELECT r FROM Employee e JOIN e.role r WHERE e.employee_id = :employee_id")
    List<Role> findHierarchyByEmployeeId(int employee_id);

    /**
     * This method is used to findout if a person is employed or not.
     * @param personId The person id of the employee.
     * @return {@code boolean} Returns true of false depending on if the employee exists.
     */
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Employee e WHERE e.person.person_id = :personId")
    boolean existsByPersonId(int personId);
}