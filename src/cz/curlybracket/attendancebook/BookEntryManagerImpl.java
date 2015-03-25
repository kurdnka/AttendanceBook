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

    public BookEntryManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private SerialBlob createUuidBlob(UUID uuid) throws SQLException {
        ByteBuffer blob = ByteBuffer.wrap(new byte[16]);
        blob.putLong(uuid.getMostSignificantBits());
        blob.putLong(uuid.getLeastSignificantBits());
        return new SerialBlob(blob.array());
    }



    /**
	 *
	 * @param entry
	 */
	public BookEntry createBookEntry(BookEntry entry) throws Exception {

        UUID employeeId = entry.getEmployee().getId();


        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement(
                    "insert into BOOK_ENTRIES (EMPLOYEE_ID, START_DATE, END_DATE, TYPE) values (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                st.setBlob(1, createUuidBlob(employeeId));
                st.setObject(2, entry.getStartDate(), Types.DATE);
                st.setObject(3, entry.getEndDate(), Types.DATE);
                st.setString(4, entry.getType().toString());
                st.executeUpdate();
                try (ResultSet keys = st.getGeneratedKeys()) {
                    if (keys.next()) {
                        UUID id = UUID.nameUUIDFromBytes(keys.getBlob(1).getBytes(0, (int) keys.getBlob(1).length()));
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
	public void updateBookEntry(BookEntry entry) {
		// TODO - implement BookEntryManagerImpl.updateBookEntry
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param entry
	 */
	public void deleteBookEntry(BookEntry entry) {
		// TODO - implement BookEntryManagerImpl.deleteBookEntry
		throw new UnsupportedOperationException();
	}

    @Override
    public List<BookEntry> findAllBookEntries() {
        return null;
    }

}