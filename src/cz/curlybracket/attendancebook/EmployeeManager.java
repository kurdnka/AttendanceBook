package cz.curlybracket.attendancebook;

import java.util.List;
import java.util.UUID;

public interface EmployeeManager {

	/**
	 *
	 * @param employee
	 */
	Employee createEmployee(Employee employee);

	/**
	 *
	 * @param employee
	 */
	void updateEmployee(Employee employee);

	/**
	 *
	 * @param employee
	 */
	void deleteEmployee(Employee employee);

	List<Employee> listAllEmployees();

	/**
	 *
	 * @param id
	 */
	Employee getEmployeeById(UUID id);

}