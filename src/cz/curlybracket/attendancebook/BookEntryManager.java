package cz.curlybracket.attendancebook;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;

import java.util.List;

public interface BookEntryManager {

	/**
	 *
	 * @param entry
	 */
	BookEntry createBookEntry(BookEntry entry);

	/**
	 *
	 * @param entry
	 */
	void updateBookEntry(BookEntry entry);

	/**
	 *
	 * @param entry
	 */
	void deleteBookEntry(BookEntry entry);

	/*
	 *
	 * @return s
	 */
	List<BookEntry> findAllBookEntries ();

}