package cz.curlybracket.attendancebook;

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
	 * @param String
	 */
	List<Employee> findEmployeesPresentByPosition(position String);

	/**
	 *
	 * @param employee
	 */
	SortedList<Employee> getAttendanceHistoryByEmployee(Employee employee);

}