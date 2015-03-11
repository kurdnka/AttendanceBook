package cz.curlybracket.attendancebook;

import java.util.List;
import java.util.UUID;

public interface EmployeeManager {

    /**
     * Creates new employee with unique ID and adds it to database, throws IllegalArgumentException if argument is null
     *
     * @param employee - employee to be added
     * @return employee - added employee
     * @throws java.lang.IllegalArgumentException - if argument is null
     */
	public Employee createEmployee(Employee employee);

    /**
     * Replaces specific employee contained in database, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
     * @param employee - employee to be replaced
     * @throws java.lang.IllegalArgumentException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
     */
	public void updateEmployee(Employee employee);

    /**
     * Deletes specific employee from database, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
     * @param employee - employee to be deleted
     * @throws java.lang.IllegalArgumentException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
     */
	public void deleteEmployee(Employee employee);

    /**
     * Returns unmodifiable list of all employees stored in database
     *
     * @return employees - unmodifiable list of all employees
     */
	public List<Employee> listAllEmployees();

    /**
     * Returns employee with specified ID, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
     * @param id - ID of employee to be returned
     * @throws java.lang.IllegalArgumentException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
     */
	public Employee getEmployeeById(UUID id);

}