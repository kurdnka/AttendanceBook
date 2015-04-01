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
		return this.employee;
	}

	/**
	 *
	 * @param employee
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
		return this.type;
	}

	/**
	 *
	 * @param type
	 */
	public void setType(EntryType type) {
		this.type = type;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntry bookEntry = (BookEntry) o;

        if (id != null ? !id.equals(bookEntry.id) : bookEntry.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BookEntry{" +
                "id=" + id +
                ", employee=" + employee +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", type=" + type +
                '}';
    }
}