package cz.curlybracket.attendancebook;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class BookEntryManagerImpl implements BookEntryManager {


    private final DataSource dataSource;
    private final EmployeeManager employeeManager;

    public BookEntryManagerImpl(DataSource dataSource, EmployeeManager employeeManager) {
        this.dataSource = dataSource;
        this.employeeManager = employeeManager;
    }

    private static Blob createUuidBlob(UUID uuid) throws SQLException {
        ByteBuffer blob = ByteBuffer.wrap(new byte[16]);
        blob.putLong(uuid.getMostSignificantBits());
        blob.putLong(uuid.getLeastSignificantBits());
        return new SerialBlob(blob.array());
    }

    private static UUID getUuidFromBlob(Blob blob) throws SQLException {
        UUID id = UUID.nameUUIDFromBytes(blob.getBytes(0, (int) blob.length()));
        blob.free();

        return id;
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
                st.setBlob(1, createUuidBlob(entry.getEmployee().getId()));
                st.setObject(2, entry.getStartDate(), Types.DATE);
                st.setObject(3, entry.getEndDate(), Types.DATE);
                st.setString(4, entry.getType().toString());
                st.executeUpdate();
                try (ResultSet keys = st.getGeneratedKeys()) {
                    if (keys.next()) {
                        UUID id = getUuidFromBlob(keys.getBlob(1));
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
                st.setBlob(1,  createUuidBlob(entry.getEmployee().getId()));
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
    public List<BookEntry> findAllBookEntries() {
        return null;
    }

    @Override
    public BookEntry getBookEntryById(UUID id) throws Exception {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("select * from BOOK_ENTRIES where ID = ?")) {
                st.setBlob(1, createUuidBlob(id));
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        Date endDate = rs.getDate("END_DATE");
                        Date startDate = rs.getDate("START_DATE");
                        EntryType type = EntryType.valueOf(rs.getString("TYPE"));
                        UUID employeeId = getUuidFromBlob(rs.getBlob("EMPLOYEE_ID"));

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
            throw new Exception("database select failed", e);
        }
    }

}