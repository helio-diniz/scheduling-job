/**
 * 
 */
package com.ciandt.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ciandt.model.Job;

/**
 * This class implements a repository for the scheduling jobs.
 * 
 * @author helio
 * @version 1.0
 */
public class SchedulingJobs {

	/** the job list. */
	private List<Job> jobList;

	/**
	 * Default constructor.
	 */
	public SchedulingJobs() {
		super();
		jobList = new ArrayList<Job>();
	}

	/**
	 * Adds a job to the JobList.
	 * 
	 * @param job
	 *        a job to be added.
	 */
	public void add(Job job) {
		jobList.add(job);
	}

	/**
	 * Gets the size of the job list.
	 * 
	 * @return the size of the job list.
	 */
	public int size() {
		return jobList.size();
	}

	/**
	 * Gets a job in the index position int the job list.
	 * 
	 * @param index
	 *        the position int the job list.
	 * 
	 * @return job in the index position int the job list,
	 */
	public Job get(int index) {
		return jobList.get(index);
	}

	/**
	 * Sorts the job list.
	 */
	public void sort() {
		Collections.sort(this.jobList);
	}

	/**
	 * Organizes the jobs of a certain period in a 8-hour schedule.
	 * 
	 * @param start
	 *        the start time of a period.
	 * 
	 * @param end
	 *        the end time of a periord.
	 * 
	 * @return the organized schedule.
	 */
	public List<List<Integer>> organize(LocalDateTime start, LocalDateTime end) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		this.sort();
		Long duration = new Long(0);

		for (Job job : this.jobList) {
			LocalDateTime jobStart = job.getDeadline().minusHours(job.getDuration());
			if (start.compareTo(jobStart) <= 0 && end.compareTo(job.getDeadline()) >= 0) {
				List<Integer> jobSequence = null;
				if (duration == 0L) {
					jobSequence = new ArrayList<Integer>();
					result.add(jobSequence);
					duration += job.getDuration();
				} else if ((duration + job.getDuration()) > 8L) {
					jobSequence = new ArrayList<Integer>();
					result.add(jobSequence);
					duration = 0L;
				} else {
					jobSequence = result.get(result.size() - 1);
					duration += job.getDuration();
				}
				jobSequence.add(job.getId());
			} else {
				continue;
			}
		}
		return result;
	}

}
