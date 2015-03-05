package cz.curlybracket.attendancebook;

public interface BookEntryManager {

	/**
	 *
	 * @param entry
	 */
	void createNewBookEntry(BookEntry entry);

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

}