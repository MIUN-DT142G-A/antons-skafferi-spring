package se.antons_skafferi.dataClass;

import jakarta.persistence.*;
import java.sql.Date;

/**
 * <h1>Employee class</h1>
 * Represents an employee entity in the database.
 */
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employee_id;
    private Date hiring_date;
    private Integer salary;
    private String password;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    /**
     * getter to get the employee id.
     * @return employee_id
     */
    public Integer getEmployee_id() {
        return employee_id;
    }

    /**
     * getter to get the hiring date.
     * @return hiring_date
     */
    public Date getHiring_date() {
        return hiring_date;
    }

    /**
     * getter to get the salary.
     * @return salary
     */
    public Integer getSalary() {
        return salary;
    }

    /**
     * getter to get the password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * getter to get the person.
     * @return person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * setter to set the password.
     * @param password The password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter to get the role.
     * @return role
     */
    public Role getRole() {
        return role;
    }

    /**
     * setter to set the role.
     * @param role The role.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * setter to set the person.
     * @param person The person.
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}