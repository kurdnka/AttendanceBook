package cz.curlybracket.attendancebook;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;
import java.nio.ByteBuffer;
import java.sql.*;
import java.util.*;

public class EmployeeManagerImpl implements EmployeeManager {

    private final DataSource dataSource;

    public EmployeeManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private SerialBlob createUuidBlob(UUID uuid) throws SQLException {
        ByteBuffer blob = ByteBuffer.wrap(new byte[16]);
        blob.putLong(uuid.getMostSignificantBits());
        blob.putLong(uuid.getLeastSignificantBits());
        return new SerialBlob(blob.array());
    }

    private List<Employee> employees = new ArrayList<Employee>();

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
                        UUID id = UUID.nameUUIDFromBytes(keys.getBlob(1).getBytes(0, (int) keys.getBlob(1).length()));
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
	public void updateEmployee(Employee employee) {
        if(employee == null) {
            throw new NullPointerException("Employee cannot be null.");
        }
        for(Employee tempEmployee : employees) {
            if (tempEmployee.equals(employee)) {
                employees.set(employees.indexOf(tempEmployee), employee);
                return;
            }
        }
        throw new NoSuchElementException("No such employee in list.");
	}

	/**
	 * Deletes specific employee from database, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
	 * @param employee - employee to be deleted
     * @throws java.lang.NullPointerException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
	 */
	public void deleteEmployee(Employee employee) {
        if(employee == null) {
            throw new NullPointerException("Employee cannot be null.");
        }
        for(Employee tempEmployee : employees) {
            if (tempEmployee.equals(employee)) {
                employees.remove(employee);
                return;
            }
        }
        throw new NoSuchElementException("No such employee in list.");
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
     * @throws java.lang.NullPointerException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
	 */
	public Employee getEmployeeById(UUID id) {
        if(id == null) {
            throw new NullPointerException("ID cannot be null.");
        }
        for(Employee employee : employees) {
            if (employee.getId().equals(id)){
                return employee;
            }
        }
        throw new NoSuchElementException("No such employee with specified ID in database.");
	}

}