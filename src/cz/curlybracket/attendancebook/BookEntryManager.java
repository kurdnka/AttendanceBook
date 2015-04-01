package cz.curlybracket.attendancebook;

import java.util.List;

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
	List<BookEntry> findAllBookEntries () throws Exception;

    /**
     * Returns employee with specified ID, throws IllegalArgumentException if argument is null or
     * NoSuchElementException if specified employee is not in database
     *
     * @param id - ID of employee to be returned
     * @throws java.lang.NullPointerException - if argument is null
     * @throws java.util.NoSuchElementException - if specified employee is not in database
     */
    BookEntry getBookEntryById(Long id) throws Exception;

}