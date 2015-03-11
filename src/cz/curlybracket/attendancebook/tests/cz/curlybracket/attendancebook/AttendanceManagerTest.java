package cz.curlybracket.attendancebook;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class AttendanceManagerTest {

    private Class attendanceManagerImpl;

    @Parameters
    public static Collection<Class> getParameters() {
        return Arrays.asList(new Class[]{
                        AttendanceManagerImpl.class
                }
        );
    }

    public AttendanceManagerTest(Class attendanceManagerImpl) {
        this.attendanceManagerImpl = attendanceManagerImpl;
    }

    @Before
    public void setUp() throws Exception {
        AttendanceManager attendanceManager = (AttendanceManager) this.attendanceManagerImpl.newInstance();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFindEmployeesByAttendanceType() throws Exception {

    }

    @Test
    public void testDoesEmployeeFulfillTheirWorkLoad() throws Exception {

    }

    @Test
    public void testFindEmployeesPresentByPosition() throws Exception {

    }

    @Test
    public void testGetAttendanceHistoryByEmployee() throws Exception {

    }
}