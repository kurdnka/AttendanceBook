package cz.curlybracket.attendancebook;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceManagerImpl implements AttendanceManager {

    // TODO - implement employeeManager and bookEntryManager in constructor and implement 2 methods with date

    private final DataSource dataSource;
    private EmployeeManager employeeManager;
    private BookEntryManager bookEntryManager;

    public AttendanceManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    /**
	 *
	 * @param type
	 */
	public List<Employee> findEmployeesByAttendanceType(EntryType type) throws Exception {
        List<Employee> list = new ArrayList<>();

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("SELECT * FROM BOOK_ENTRIES WHERE TYPE = ?")) {
                st.setString(1, type.toString());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        list.add(employeeManager.getEmployeeById(rs.getLong("EMPLOYEE_ID")));
                    }
                }
            }
        } catch (SQLException ex) {
            throw new Exception("Database select failed", ex);
        }
        return list;
	}

	/**
	 *
	 * @param employee
	 */
	public boolean doesEmployeeFulfillTheirWorkLoad(Employee employee) {
		// TODO - implement AttendanceManagerImpl.doesEmployeeFulfillTheirWorkLoad
		return true;
	}

	/**
	 *
	 * @param position
	 */
	public List<Employee> findEmployeesPresentByPosition(String position) {
		// TODO - implement AttendanceManagerImpl.findEmployeesPresentByPosition
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param employee
	 */
	public List<BookEntry> getAttendanceHistoryByEmployee(Employee employee) throws Exception {
        List<BookEntry> list = new ArrayList<>();

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("SELECT * FROM BOOK_ENTRIES WHERE EMPLOYEE_ID = ?")) {
                st.setLong(1, employee.getId());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        list.add(bookEntryManager.getBookEntryById(rs.getLong("ID")));
                    }
                }
            }
        } catch (SQLException ex) {
            throw new Exception("Database select failed", ex);
        }
        return list;
	}


}