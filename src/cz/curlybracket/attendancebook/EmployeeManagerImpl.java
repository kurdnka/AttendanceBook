package cz.curlybracket.attendancebook;

import java.util.*;

public class EmployeeManagerImpl {

    private List<Employee> employees = new ArrayList<Employee>();

	/**
	 * Creates new employee with unique ID and adds it to database, throws IllegalArgumentException if argument is null
     *
	 * @param employee - employee to be added
     * @return employee - added employee
     * @throws java.lang.IllegalArgumentException - if argument is null
	 */
	public Employee createEmployee(Employee employee) {
        if(employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        return employee;
	}

	/**
	 * Replaces specific employee contained in database, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
	 *
     * @param employee - employee to be replaced
     * @throws java.lang.IllegalArgumentException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
	 */
	public void updateEmployee(Employee employee) {
        if(employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        if(!employees.contains(employee)) {
            throw new NoSuchElementException("No such employee in list.");
        }
        for(Employee tempEmployee : employees) {
            if (tempEmployee.equals(employee)) {
                employees.set(employees.indexOf(tempEmployee), employee);
                return;
            }
        }
	}

	/**
	 * Deletes specific employee from database, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
	 * @param employee - employee to be deleted
     * @throws java.lang.IllegalArgumentException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
	 */
	public void deleteEmployee(Employee employee) {
        if(employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        if(!employees.contains(employee)) {
            throw new NoSuchElementException("No such employee in list.");
        }
        for(Employee tempEmployee : employees) {
            if (tempEmployee.equals(employee)) {
                employees.remove(employee);
                return;
            }
        }
	}

    /**
     * Returns unmodifiable list of all employees stored in database
     *
     * @return employees - unmodifiable list of all employees
     */
	public List<Employee> listAllEmployees() {
		return Collections.unmodifiableList(employees);
	}

	/**
	 * Returns employee with specified ID, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
	 * @param id - ID of employee to be returned
     * @throws java.lang.IllegalArgumentException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
	 */
	public Employee getEmployeeById(UUID id) {
        if(id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
        for(Employee employee : employees) {
            if (employee.getId().compareTo(id) == 0) {
                return employee;
            }
        }
        throw new NoSuchElementException("No such employee with specified ID in database.");
	}

}