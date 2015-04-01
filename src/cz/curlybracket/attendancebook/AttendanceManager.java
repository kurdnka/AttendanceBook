package cz.curlybracket.attendancebook;

import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.List;

public interface AttendanceManager {

	/**
	 *
	 * @param type
	 */
	List<Employee> findEmployeesByAttendanceType(EntryType type) throws Exception;

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
	List<BookEntry> getAttendanceHistoryByEmployee(Employee employee) throws Exception;
}