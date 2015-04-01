package cz.curlybracket.attendancebook;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;
import java.nio.ByteBuffer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookEntryManagerImpl implements BookEntryManager {


    private final DataSource dataSource;
    private final EmployeeManager employeeManager;

    public BookEntryManagerImpl(DataSource dataSource, EmployeeManager employeeManager) {
        this.dataSource = dataSource;
        this.employeeManager = employeeManager;
    }



    /**
	 *
	 * @param entry
	 */
	public BookEntry createBookEntry(BookEntry entry) throws Exception {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(
                    "insert into BOOK_ENTRIES (EMPLOYEE_ID, START_DATE, END_DATE, TYPE) values (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                st.setLong(1, entry.getEmployee().getId());
                st.setObject(2, entry.getStartDate(), Types.DATE);
                st.setObject(3, entry.getEndDate(), Types.DATE);
                st.setString(4, entry.getType().toString());
                st.executeUpdate();
                try (ResultSet keys = st.getGeneratedKeys()) {
                    if (keys.next()) {
                        Long id = keys.getLong(1);
                        entry.setId(id);
                    }
                }
                return entry;
            }
        } catch (SQLException e) {
            throw new Exception("database insert failed", e);
        }
    }

    /**
	 *
	 * @param entry
	 */
	public void updateBookEntry(BookEntry entry) throws Exception {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(
                    "update BOOK_ENTRIES set EMPLOYEE_ID=?, START_DATE=?, END_DATE=?, TYPE=? where ID=?")) {
                st.setLong(1, entry.getEmployee().getId());
                st.setObject(2, entry.getStartDate(), Types.DATE);
                st.setObject(3, entry.getEndDate(), Types.DATE);
                st.setString(4, entry.getType().toString());
                int n = st.executeUpdate();
                if (n != 1) {
                    throw new Exception("not updated book with id " + entry.getId(), null);
                }
            }
        } catch (SQLException e) {
            throw new Exception("database update failed", e);
        }
	}

	/**
	 *
	 * @param entry
	 */
	public void deleteBookEntry(BookEntry entry) throws Exception {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(
                    "delete from BOOK_ENTRIES where ID=?")) {
                st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("database delete failed", e);
        }
	}

    @Override
    public List<BookEntry> findAllBookEntries() throws Exception {
        List<BookEntry> list = new ArrayList<>();

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("SELECT * FROM BOOK_ENTRIES")) {
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        BookEntry entry = new BookEntry();
                        entry.setStartDate(rs.getDate("START_DATE"));
                        entry.setEndDate(rs.getDate("END_DATE"));
                        entry.setEmployee(employeeManager.getEmployeeById(rs.getLong("EMPLOYEE_ID")));
                        entry.setType(EntryType.valueOf(rs.getString("TYPE")));
                        list.add(entry);
                    }
                }
            }
        } catch (SQLException ex) {
            throw new Exception("Database select failed", ex);
        }
        return list;
    }

    @Override
    public BookEntry getBookEntryById(Long id) throws Exception {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("select * from BOOK_ENTRIES where ID = ?")) {
                st.setLong(1, id);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        Date endDate = rs.getDate("END_DATE");
                        Date startDate = rs.getDate("START_DATE");
                        EntryType type = EntryType.valueOf(rs.getString("TYPE"));
                        Long employeeId = rs.getLong("EMPLOYEE_ID");

                        BookEntry entry = new BookEntry();
                        entry.setId(id);
                        entry.setStartDate(startDate);
                        entry.setEndDate(endDate);
                        entry.setEmployee(employeeManager.getEmployeeById(employeeId));
                        entry.setType(type);
                        return entry;
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("Database select failed", e);
        }
    }

}