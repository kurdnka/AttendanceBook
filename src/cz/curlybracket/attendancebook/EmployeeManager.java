package cz.curlybracket.attendancebook;

import java.util.List;

public interface EmployeeManager {

    /**
     * Creates new employee with unique ID and adds it to database, throws IllegalArgumentException if argument is null
     *
     * @param employee - employee to be added
     * @return employee - added employee
     * @throws java.lang.NullPointerException - if argument is null
     */
    Employee createEmployee(Employee employee) throws Exception;

    /**
     * Replaces specific employee contained in database, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
     * @param employee - employee to be replaced
     * @throws java.lang.NullPointerException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
     */
    void updateEmployee(Employee employee) throws Exception;

    /**
     * Deletes specific employee from database, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
     * @param employee - employee to be deleted
     * @throws java.lang.NullPointerException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
     */
    void deleteEmployee(Employee employee) throws Exception;

    /**
     * Returns unmodifiable list of all employees stored in database
     *
     * @return employees - unmodifiable list of all employees
     */
    List<Employee> listAllEmployees() throws Exception;

    /**
     * Returns employee with specified ID, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
     * @param id - ID of employee to be returned
     * @throws java.lang.NullPointerException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
     */
    Employee getEmployeeById(Long id) throws Exception;

}