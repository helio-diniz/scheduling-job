package com.ciandt.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ciandt.model.Job;
import com.ciandt.repository.SchedulingJobs;
import com.ciandt.utils.JSONUtils;

/**
 * This class implements the services supplied for the jobs.
 * 
 * @author Helio Diniz
 * @version 1.0
 */
public class JobService {

  /** the Scheduling Job repository. */
  private SchedulingJobs jobRepository;
  /** the JSON handling library. */
  private JSONUtils jsonUtils;
  /** the resulting schedule */
  private List<List<Long>> resultingSchedule;

  /**
   * Default constructor.
   * 
   * @param jobRepository
   *        the Scheduling Job repository.
   * @param jsonUtils
   *        the JSON handling library.
   */
  public JobService(SchedulingJobs jobRepository, JSONUtils jsonUtils) {
    super();
    this.jobRepository = jobRepository;
    this.jsonUtils = jsonUtils;
  }

  /**
   * Gets the Scheduling Job repository.
   * 
   * @return the Scheduling Job repository
   */
  public SchedulingJobs getJobRepository() {
    return this.jobRepository;
  }

  /**
   * Gets the JSON handling library.
   * 
   * @return the JSON handling library.
   */
  public JSONUtils getJsonUtils() {
    return this.jsonUtils;
  }

  /**
   * Gets all jobs from a JSON file.
   * 
   * @param filename
   *        the file name of a JSON file.
   */
  public void getAllJobs(String filename) {

    Object object = null;
    try {
      object = this.jsonUtils.read(filename);
    } catch (Exception e) {
      return;
    }

    if (object instanceof JSONArray) {
      JSONArray jsonArray = (JSONArray) object;
      this.jobRepository.clear();
      try {
        for (int index = 0; index < jsonArray.size(); index++) {
          JSONObject jsonObject = (JSONObject) jsonArray.get(index);

          this.jobRepository.add(new Job((Long) jsonObject.get("id"), (String) jsonObject.get("description"),
              (String) jsonObject.get("deadlineText"), (Long) jsonObject.get("duration")));
        }
      } catch (Exception e) {
        this.jobRepository.clear();
      }
    }
  }

  /**
   * Saves the repository job list to a JSON file.
   * 
   * @param filename
   *        the JSON filename to be saved.
   */
  public void saveJobs(String filename) {
    try {
      this.jsonUtils.write(this.jobRepository.getJobList(), filename);
    } catch (IOException e) {
    }
  }

  /**
   * Compares two JSON files.
   * 
   * @param inputFilename
   *        the full path name of the input JSON file.
   * @param outputFilename
   *        the full path name of the output JSON file.
   * @return true, if the two JSON objects are equal, or false, otherwise.
   */
  public boolean compareJobs(String inputFilename, String outputFilename) {
    return this.jsonUtils.compare(inputFilename, outputFilename);
  }

  /**
   * Organizes the jobs of a certain period in the repository in a 8-hour
   * schedule.
   * 
   * @param start
   *        the start time of a period.
   * 
   * @param end
   *        the end time of a periord.
   */
  public void organizeSchedulingJobs(LocalDateTime start, LocalDateTime end) {
    this.resultingSchedule = this.jobRepository.organize(start, end);
  }

  /**
   * Gets the resulting schedule which are last organized.
   * 
   * @return the resulting schedule.
   */
  public List<List<Long>> getResultingSchedule() {
    return this.resultingSchedule;
  }

}
