package cz.curlybracket.attendancebook;

public class BookEntry {

	private UUID id;
	private Employee employee;
	private Date startDate;
	private Date endDate;
	private EntryType type;

	public UUID getId() {
		return this.id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	public Employee getEmployeeId() {
		// TODO - implement BookEntry.getEmployeeId
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param Employee
	 */
	public void setEmployeeId(UUID Employee) {
		// TODO - implement BookEntry.setEmployeeId
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

	public AttendanceType getType() {
		// TODO - implement BookEntry.getType
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param type
	 */
	public void setType(AttendanceType type) {
		// TODO - implement BookEntry.setType
		throw new UnsupportedOperationException();
	}

}