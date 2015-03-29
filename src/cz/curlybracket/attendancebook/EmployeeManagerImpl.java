package cz.curlybracket.attendancebook;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class EmployeeManagerImpl implements EmployeeManager {

    private final DataSource dataSource;

    public EmployeeManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


	/**
	 * Creates new employee with unique ID and adds it to database, throws IllegalArgumentException if argument is null
     *
	 * @param employee - employee to be added
     * @return employee - added employee
     * @throws java.lang.NullPointerException - if argument is null
	 */
	public Employee createEmployee(Employee employee) throws Exception {
        if(employee == null) {
            throw new NullPointerException("Employee cannot be null.");
        }
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(
                    "insert into EMPLOYEES (NAME, OFFICE_NUMBER, POSITION, WORK_LOAD) values (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                st.setString(1, employee.getName());
                st.setInt(2, employee.getOfficeNumber());
                st.setString(3, employee.getPosition());
                st.setInt(4, employee.getWorkLoad());
                st.executeUpdate();
                try (ResultSet keys = st.getGeneratedKeys()) {
                    if (keys.next()) {
                        Long id = keys.getLong(1);
                        employee.setId(id);
                    }
                } catch (Exception ex){
                    throw new Exception("Generating UUID failed.", ex);
                }
                return employee;
            }
        } catch (SQLException ex) {
            throw new Exception("Database insert failed.", ex);
        }
	}

	/**
	 * Replaces specific employee contained in database, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
	 *
     * @param employee - employee to be replaced
     * @throws java.lang.NullPointerException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
	 */
	public void updateEmployee(Employee employee) throws Exception {
        if(employee == null) {
            throw new NullPointerException("Employee cannot be null.");
        }
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(
                    "update EMPLOYEES set NAME=?, OFFICE_NUMBER=?, POSITION=?, WORK_LOAD=? where ID=?")) {
                st.setString(1, employee.getName());
                st.setInt(2, employee.getOfficeNumber());
                st.setString(3, employee.getPosition());
                st.setInt(4, employee.getWorkLoad());
                int n = st.executeUpdate();
                if (n != 1) {
                    throw new Exception("Did not update employee with id " + employee.getId(), null);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Database update failed.", e);
        }
	}

	/**
	 * Deletes specific employee from database, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
	 * @param employee - employee to be deleted
     * @throws java.lang.NullPointerException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
	 */
	public void deleteEmployee(Employee employee) throws Exception {
        if(employee == null) {
            throw new NullPointerException("Employee cannot be null.");
        }
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(
                    "delete from EMPLOYEES where ID=?")) {
                st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("database delete failed", e);
        }
        throw new NoSuchElementException("No such employee in list.");
	}

    /**
     * Returns unmodifiable list of all employees stored in database
     *
     * @return employees - unmodifiable list of all employees
     */
	public List<Employee> listAllEmployees() {
		return null;
	}

	/**
	 * Returns employee with specified ID, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
	 * @param id - ID of employee to be returned
     * @throws java.lang.NullPointerException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
	 */
	public Employee getEmployeeById(Long id) throws Exception {
        if (id == null) {
            throw new NullPointerException("ID cannot be null.");
        }
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("SELECT * FROM EMPLOYEES WHERE ID = ?")) {
                st.setLong(1, id);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        Employee employee = new Employee();
                        employee.setId(id);
                        employee.setName(rs.getString("NAME"));
                        employee.setOfficeNumber(rs.getInt("OFFICE_NUMBER"));
                        employee.setPosition(rs.getString("POSITION"));
                        employee.setWorkLoad(rs.getInt("OFFICE_NUMBER"));
                        return employee;
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("database select failed", e);
        }
    }

}