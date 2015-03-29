package cz.curlybracket.attendancebook;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;

import java.util.List;
import java.util.UUID;

public interface BookEntryManager {

	/**
	 *
	 * @param entry
	 */
	BookEntry createBookEntry(BookEntry entry) throws Exception;

	/**
	 *
	 * @param entry
	 */
	void updateBookEntry(BookEntry entry) throws Exception;

	/**
	 *
	 * @param entry
	 */
	void deleteBookEntry(BookEntry entry) throws Exception;

	/*
	 *
	 * @return s
	 */
	List<BookEntry> findAllBookEntries ();

    /**
     * Returns employee with specified ID, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
     * @param id - ID of employee to be returned
     * @throws java.lang.NullPointerException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
     */
    BookEntry getBookEntryById(UUID id) throws Exception;

}