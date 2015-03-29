package cz.curlybracket.attendancebook;

import java.util.Date;

public class BookEntry {

	private Long id;
	private Employee employee;
	private Date startDate;
	private Date endDate;
	private EntryType type;

	public Long getId() {
		return this.id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		// TODO - implement BookEntry.getEmployee
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param employee
	 */
	public void setEmployee(Employee employee) {
		// TODO - implement BookEntry.setEmployee
		throw new UnsupportedOperationException();
	}

	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 *
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 *
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public EntryType getType() {
		// TODO - implement BookEntry.getType
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param type
	 */
	public void setType(EntryType type) {
		// TODO - implement BookEntry.setType
		throw new UnsupportedOperationException();
	}

}