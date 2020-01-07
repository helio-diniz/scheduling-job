package com.ciandt.json;

import java.time.LocalDate;

/**
 * Implement an employee data source.
 * 
 * @author helio
 * @version 1.0
 */
public class Employee {
	private Integer id;
	private String name;
	private String surname;
	private LocalDate birthday;
	private Double salary;
	private boolean employed;

	/**
	 * Default constructor.
	 * 
	 * @param id
	 *        the employee's ID. 
	 * @param name
	 *        the employee's name
	 * @param surname
	 *        the employee's surname;
	 * @param birthday
	 *        the employee's birthday;
	 * @param salary
	 *        the employee's salary;  
	 * @param employed
	 *        the employee's status.
	 */
	public Employee(Integer id, String name, String surname, LocalDate birthday, Double salary, boolean employed) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.salary = salary;
		this.employed = employed;
	}

	/**
	 * Gets the employee's ID.
	 * 
	 * @return the employee's ID.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Gets the employee's name.
	 * 
	 * @return the employee's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the employee's surname.
	 * 
	 * @return the employee's surname.
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Gets the employee's birthday.
	 * 
	 * @return the employee's birthday.
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * Gets the employee's salary.
	 * 
	 * @return the employee's salary.
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * Gets the employee's status.
	 * 
	 * @return the employee's status.
	 */
	public boolean isEmployed() {
		return employed;
	}

}
