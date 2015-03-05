package cz.curlybracket.attendancebook;

import javafx.collections.transformation.SortedList;

import java.util.List;

public interface AttendanceManager {

	/**
	 *
	 * @param type
	 */
	List<Employee> findEmployeesByAttendanceType(EntryType type);

	/**
	 *
	 * @param employee
	 */
	boolean doesEmployeeFulfillTheirWorkLoad(Employee employee);

	/**
	 *
	 * @param position
	 */
	List<Employee> findEmployeesPresentByPosition(String position);

	/**
	 *
	 * @param employee
	 */
	SortedList<Employee> getAttendanceHistoryByEmployee(Employee employee);

}