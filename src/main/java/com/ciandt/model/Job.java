package com.ciandt.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class implements a job model.
 * 
 * @author helio
 * @version 1.0
 */
public class Job implements Comparable<Job> {
	/** Job's ID. */
	private Long id;
	/** Job's description. */
	private String description;
	/** Job's deadline. */
	private transient LocalDateTime deadline;
	/** Job's deadline in formatted text. */
	private String deadlineText;
	/** Job's estimated duration. */
	private Long duration;

	/**
	 * Defaul constructor.
	 * 
	 * @param id
	 *        the Job's ID.
	 * @param description
	 *        the Job's description.
	 * @param deadline
	 *        the Job's deadline
	 * @param durarion
	 *        the Job's estimated duration.
	 */
	public Job(Long id, String description, String deadlineText, Long durarion) {
		super();
		this.id = id;
		this.description = description;
		this.setDeadlineText(deadlineText);
		this.duration = durarion;
	}

	/**
	 * Gets the job's id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the job's id.
	 * 
	 * @param id
	 *          the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the job's description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the job's description.
	 * 
	 * @param description
	 *        the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the job's deadline.
	 * 
	 * @return the deadline
	 */
	public LocalDateTime getDeadline() {
		return deadline;
	}

	/**
	 * Sets the job's deadline.
	 * 
	 * @param deadline
	 *        the deadline to set
	 */
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	/**
	 * Gets the job's duration.
	 * 
	 * @return the durarion
	 */
	public Long getDuration() {
		return duration;
	}

	/**
	 * Gets the job's duration.
	 * 
	 * @param durarion
	 *        the durarion to set
	 */
	public void setDuration(Long durarion) {
		this.duration = durarion;
	}

	/**
	 * Gets the job's deadline in formatted text.
	 * 
	 * @return the job's deadline in formatted text.
	 */
	public String getDeadlineText() {
		return this.deadlineText;
	}

	/**
	 * Sets the job's deadline in formatted text.
	 * 
	 * @return the job's deadline in formatted text.
	 */
	public void setDeadlineText(String deadlineText) {

		try {
			this.deadline = LocalDateTime.parse(deadlineText, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			this.deadlineText = deadlineText;
		} catch (Exception e) {
			this.deadline = null;
			this.deadlineText = "";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Job job) {
		if (this.getDeadlineText().compareTo(job.getDeadlineText()) < 0) {
			return -1;
		}
		if (this.getDeadlineText().compareTo(job.getDeadlineText()) > 0) {
			return 1;
		}
		if (this.getDuration().compareTo(job.getDuration()) < 0) {
			return -1;
		}
		if (this.getDuration().compareTo(job.getDuration()) > 0) {
			return 1;
		}
		return 0;
	}

}
