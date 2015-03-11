package cz.curlybracket.attendancebook;

import java.util.*;

public class EmployeeManagerImpl {

    private List<Employee> employees = new ArrayList<Employee>();

	/**
	 *
	 * @param employee
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
	 *
	 * @param employee
	 */
	public void updateEmployee(Employee employee) {

	}

	/**
	 *
	 * @param employee
	 */
	public void deleteEmployee(Employee employee) {
		// TODO - implement EmployeeManagerImpl.deleteEmployee
		throw new UnsupportedOperationException();
	}

	public List<Employee> listAllEmployees() {
		return Collections.unmodifiableList(employees);
	}

	/**
	 *
	 * @param id
	 */
	public Employee getEmployeeById(UUID id) {
        if(id == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        for(Employee employee : employees) {
            if(employee.getId().compareTo(id) == 0) {
                return employee;
            }
        }
        throw new NoSuchElementException("No such employee in list with specified ID.");
	}

}