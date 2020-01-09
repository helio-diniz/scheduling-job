package com.ciandt.app;

import java.time.LocalDateTime;

import com.ciandt.repository.SchedulingJobs;
import com.ciandt.service.JobService;
import com.ciandt.utils.JSONUtils;

/**
 * Application class to demonstrate the implementation as proposed.
 * 
 * @author Helio Diniz
 * @version 1.0
 */
public class Application {

  /**
   * Application main method.
   * 
   * @param args
   *        arguments
   */
  public static void main(String[] args) {
    SchedulingJobs jobRepository = new SchedulingJobs();
    JSONUtils jsonUtils = new JSONUtils();
    JobService jobService = new JobService(jobRepository, jsonUtils);
    jobService.getAllJobs("./src/main/resources/input/scheduling-jobs.json");

    jobService.organizeSchedulingJobs(LocalDateTime.of(2019, 11, 10, 9, 00, 00),
        LocalDateTime.of(2019, 11, 11, 12, 00, 00));

    System.out.println("Massa de Dados");
    String jsonString = jsonUtils.toString(jobService.getJobRepository().getJobList());
    System.out.println(jsonString);

    System.out.println("");
    System.out.println("Output esperado");
    System.out.println(jobService.getResultingSchedule());
  }

}
