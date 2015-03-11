package cz.curlybracket.attendancebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class EmployeeManagerTest {


    private Class employeeManagerImpl;
    private EmployeeManager employeeManager;

    @Parameterized.Parameters
    public static Collection<Class> getParameters() {
        return Arrays.asList(new Class[]{
                        EmployeeManagerImpl.class
                }
        );
    }

    public EmployeeManagerTest (Class employeeManagerImpl) {
        this.employeeManagerImpl = employeeManagerImpl;
    }



    @Before
    public void setUp() throws Exception {
        this.employeeManager = (EmployeeManager) this.employeeManagerImpl.newInstance();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateEmployeeWithNull() throws Exception {
        try {
            employeeManager.createEmployee(null);
            fail("Did not throw a NullPointerException for empty input");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testCreateEmployeeWithId() throws Exception {
        Employee employee = new Employee();
        Employee newEmployee = employeeManager.createEmployee(employee);
        assertThat(newEmployee.getId(), is(not(equalTo(null))));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee();

        Employee newEmployee = employeeManager.createEmployee(employee);
        Employee employee2 = new Employee();
        UUID id = UUID.randomUUID();
        employee.setId(id);
        assertThat(newEmployee.getId(), is(not(equalTo(null))));
    }

    @Test
    public void testDeleteEmployee() throws Exception {

    }

    @Test
    public void testListAllEmployees() throws Exception {

    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        UUID id = UUID.randomUUID();
        employee.setId(id);
        assertThat(employeeManager.getEmployeeById(id).getId(), is(equalTo(id)));
    }
}