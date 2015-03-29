package cz.curlybracket.attendancebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class EmployeeManagerTest {


    private Class employeeManagerImpl;
    private EmployeeManager employeeManager;

    @Parameterized.Parameters
    public static Collection<Object> getParameters() {
        return Arrays.asList(
                new Object[]{
                        new Class[]{
                                EmployeeManagerImpl.class
                        }
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
    public void testUpdateEmployeeWithNull() throws Exception {
        try {
            employeeManager.updateEmployee(null);
            fail("Did not throw a NullPointerException for empty input");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testUpdateEmployeeNonExisting() throws Exception {
        try {
            employeeManager.updateEmployee(new Employee());
            fail("Did not throw a NoSuchElementException for not existing employee");
        } catch (NoSuchElementException ex) {
        }
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("Ludek") ;
        Employee newEmployee = employeeManager.createEmployee(employee);
        Employee employee2 = new Employee();
        employee2.setId(newEmployee.getId());
        employee2.setName("Bartek");
        employeeManager.updateEmployee(employee2);
        assertThat(employeeManager.getEmployeeById(newEmployee.getId()).getName(), is(equalTo("Bartek")));
    }

    @Test
    public void testDeleteEmployeeWithNull() throws Exception {
        try {
            employeeManager.deleteEmployee(null);
            fail("Did not throw a NullPointerException for empty input");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testDeleteEmployeeNonExisting() throws Exception {
        try {
            employeeManager.deleteEmployee(new Employee());
            fail("Did not throw a NoSuchElementException for not existing employee");
        } catch (NoSuchElementException ex) {
        }
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        Employee employee = new Employee();
        Employee newEmployee = employeeManager.createEmployee(employee);
        employeeManager.deleteEmployee(newEmployee);
        assertFalse(employeeManager.listAllEmployees().contains(newEmployee));
    }

    @Test
    public void testListAllEmployees() throws Exception {
        Employee employee = new Employee();
        Employee newEmployee = employeeManager.createEmployee(employee);
        assertTrue(employeeManager.listAllEmployees().contains(newEmployee));
    }

    @Test
    public void testGetEmployeeByIdWithNull() throws Exception {
        try {
            employeeManager.getEmployeeById(null);
            fail("Did not throw a NullPointerException for empty input");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testGetEmployeeByIdNonExisting() throws Exception {
        try {
            employeeManager.getEmployeeById((long) -1);
            fail("Did not throw a NoSuchElementException for not existing employee");
        } catch (NoSuchElementException ex) {
        }
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        Employee newEmployee = employeeManager.createEmployee(employee);
        assertThat(employeeManager.getEmployeeById(newEmployee.getId()).getId(), is(equalTo(newEmployee.getId())));
    }
}