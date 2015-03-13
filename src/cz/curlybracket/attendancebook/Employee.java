package cz.curlybracket.attendancebook;

import java.util.UUID;

public class Employee {

	private UUID id;
	private String name;
	private int officeNumber;
	private String position;
	private int workLoad;

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

	public String getName() {
		return this.name;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getOfficeNumber() {
		return this.officeNumber;
	}

	/**
	 *
	 * @param officeNumber
	 */
	public void setOfficeNumber(int officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getPosition() {
		return this.position;
	}

	/**
	 *
	 * @param position
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	public int getWorkLoad() {
		return this.workLoad;
	}

	/**
	 *
	 * @param workLoad
	 */
	public void setWorkLoad(int workLoad) {
		this.workLoad = workLoad;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != null ? !id.equals(employee.id) : employee.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", officeNumber=" + officeNumber +
                ", position='" + position + '\'' +
                ", workLoad=" + workLoad +
                '}';
    }
}