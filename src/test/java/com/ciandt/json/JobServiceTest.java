/**
 * 
 */
package com.ciandt.json;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.ciandt.repository.SchedulingJobs;
import com.ciandt.service.JobService;
import com.ciandt.utils.JSONUtils;

/**
 * Tests the Job Service functionalities.
 * 
 * @author Helio Diniz
 *
 */
public class JobServiceTest {
	/** the Scheduling Job repository. */
	private SchedulingJobs jobRepository;
	/** the JSON handling library. */
	private JSONUtils jsonUtils;
	/** the Job Service. */
	private JobService jobService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.jobRepository = new SchedulingJobs();
	  this.jsonUtils = new JSONUtils();
		this.jobService = new JobService(jobRepository, jsonUtils);
	}

	@Test
	/**
	 * Tests a Job Service creation and its association to a Scheduling Job Repository.
	 */
	public void createJobService() {
		
		assertEquals(this.jobRepository, this.jobService.getJobRepository());
		assertEquals(this.jsonUtils, this.jobService.getJsonUtils());
		assertNull(this.jobService.getResultingSchedule());
		
	}
	
	@Test
	/**
	 * Tests a Job Service data loading from a JSON file.
	 */
	public void findAllSchedulingJobs() {
		this.jobService.getAllJobs("./src/test/resources/input/scheduling-jobs.json");
		
		assertEquals(3, jobService.getJobRepository().size());
		assertEquals(new Long(1), jobService.getJobRepository().get(0).getId());
		assertEquals("Importação de arquivos de fundos", jobService.getJobRepository().get(0).getDescription());
		assertEquals(LocalDateTime.of(2019, 11, 10, 12, 0, 0), jobService.getJobRepository().get(0).getDeadline());
		assertEquals("2019-11-10 12:00:00", jobService.getJobRepository().get(0).getDeadlineText());
		assertEquals(new Long(2), jobService.getJobRepository().get(0).getDuration());

		assertEquals(new Long(2), jobService.getJobRepository().get(1).getId());
		assertEquals("Importação de dados da Base Legada", jobService.getJobRepository().get(1).getDescription());
		assertEquals(LocalDateTime.of(2019, 11, 11, 12, 0, 0), jobService.getJobRepository().get(1).getDeadline());
		assertEquals("2019-11-11 12:00:00", jobService.getJobRepository().get(1).getDeadlineText());
		assertEquals(new Long(4), jobService.getJobRepository().get(1).getDuration());

		assertEquals(new Long(3), jobService.getJobRepository().get(2).getId());
		assertEquals("Importação de dados de integração", jobService.getJobRepository().get(2).getDescription());
		assertEquals(LocalDateTime.of(2019, 11, 11, 8, 0, 0), jobService.getJobRepository().get(2).getDeadline());
		assertEquals("2019-11-11 08:00:00", jobService.getJobRepository().get(2).getDeadlineText());
		assertEquals(new Long(6), jobService.getJobRepository().get(2).getDuration());
	}
	
	@Test
	/**
	 * Tests a Job Service data loading from an inexisting JSON file.
	 */
	public void findAllSchedulingJobsInexistingJSON() {
		this.jobService.getAllJobs("./src/test/resources/input/invalid-filename.json");
		assertEquals(0, this.jobService.getJobRepository().size());
	}
	
	
	@Test
	/**
	 * Tests a Job Service data loading from an invalid JSON file.
	 */
	public void findAllSchedulingJobsFromInvalidJSON() {
		this.jobService.getAllJobs("./src/test/resources/input/invalid-id-scheduling-jobs.json");
		assertEquals(0, this.jobService.getJobRepository().size());
	}
	
	@Test
	/**
	 * Tests a Job Service data loading from an JSON file with a invalid leadtime formatted text.
	 */
	public void findAllSchedulingJobsFromInvalidLeadtimeJSON() {
		this.jobService.getAllJobs("./src/test/resources/input/invalid-leadtime-scheduling-jobs.json");
		assertEquals(3, this.jobService.getJobRepository().size());

		assertEquals(new Long(3), jobService.getJobRepository().get(2).getId());
		assertEquals("Importação de dados de integração", jobService.getJobRepository().get(2).getDescription());
		assertNull(jobService.getJobRepository().get(2).getDeadline());
		assertEquals("", jobService.getJobRepository().get(2).getDeadlineText());
		assertEquals(new Long(6), jobService.getJobRepository().get(2).getDuration());
	}
	
	@Test
	/**
	 * Tests a scheduling jobs repository persistence into a JSON file.
	 */
	public void saveSchedulingJobs() {
		this.jobService.getAllJobs("./src/test/resources/input/scheduling-jobs.json");
		this.jobService.saveJobs("./src/test/resources/output/scheduling-jobs.json");
		assertTrue(this.jobService.compareJobs("./src/test/resources/input/scheduling-jobs.json", 
				"./src/test/resources/output/scheduling-jobs.json"));
	}	
	
	@Test
	/**
	 * Tests a organization of all jobs from the `scheduling-jobs` JSON file
	 */
	public void organizeSchedulingJobs() {
		this.jobService.getAllJobs("./src/test/resources/input/scheduling-jobs.json");
		this.jobService.saveJobs("./src/test/resources/output/scheduling-jobs.json");
		assertTrue(this.jobService.compareJobs("./src/test/resources/input/scheduling-jobs.json", 
				"./src/test/resources/output/scheduling-jobs.json"));
		
		this.jobService.organizeSchedulingJobs(LocalDateTime.of(2019, 11, 10, 9, 00, 00),
		    LocalDateTime.of(2019, 11, 11, 12, 00, 00));
		
		assertNotNull(this.jobService.getResultingSchedule());
		assertEquals(2, this.jobService.getResultingSchedule().size());
		assertEquals(2, this.jobService.getResultingSchedule().get(0).size());
		assertEquals(new Long(1), this.jobService.getResultingSchedule().get(0).get(0));
		assertEquals(new Long(3), this.jobService.getResultingSchedule().get(0).get(1));
		assertEquals(1, this.jobService.getResultingSchedule().get(1).size());
		assertEquals(new Long(2), this.jobService.getResultingSchedule().get(1).get(0));
	}	
	
	@Test
	/**
	 * Tests a Job Service data loading from an JSON file with a invalid leadtime formatted text.
	 */
	public void organizesSchedulingJobsFromInvalidLeadtimeJSON() {
		this.jobService.getAllJobs("./src/test/resources/input/invalid-leadtime-scheduling-jobs.json");
		this.jobService.organizeSchedulingJobs(LocalDateTime.of(2010, 10, 11, 8, 00, 00),
		    LocalDateTime.of(2019, 11, 11, 12, 00, 00));
		
		assertNotNull(this.jobService.getResultingSchedule());
		assertEquals(1, this.jobService.getResultingSchedule().size());
		assertEquals(2, this.jobService.getResultingSchedule().get(0).size());
		assertEquals(new Long(1), this.jobService.getResultingSchedule().get(0).get(0));
		assertEquals(new Long(2), this.jobService.getResultingSchedule().get(0).get(1));
	}

	
	@Test
	/**
	 * Tests a organization of all jobs from the `scheduling-jobs-product` JSON file.
	 */
	public void organizeProductSchedulingJobs() {
		this.jobService.getAllJobs("./src/test/resources/input/scheduling-jobs-product.json");
		this.jobService.saveJobs("./src/test/resources/output/scheduling-jobs-product.json");
		assertTrue(this.jobService.compareJobs("./src/test/resources/input/scheduling-jobs.json", 
				"./src/test/resources/output/scheduling-jobs.json"));
		
		this.jobService.organizeSchedulingJobs(LocalDateTime.of(2020, 1, 10, 8, 00, 00),
		    LocalDateTime.of(2020, 1, 12, 18, 00, 00));
		
		assertNotNull(this.jobService.getResultingSchedule());
		assertEquals(3, this.jobService.getResultingSchedule().size());
		assertEquals(3, this.jobService.getResultingSchedule().get(0).size());
		assertEquals(new Long(1), this.jobService.getResultingSchedule().get(0).get(0));
		assertEquals(new Long(4), this.jobService.getResultingSchedule().get(0).get(1));
		assertEquals(new Long(5), this.jobService.getResultingSchedule().get(0).get(2));
		assertEquals(2, this.jobService.getResultingSchedule().get(1).size());
		assertEquals(new Long(7), this.jobService.getResultingSchedule().get(1).get(0));
		assertEquals(new Long(2), this.jobService.getResultingSchedule().get(1).get(1));
		assertEquals(2, this.jobService.getResultingSchedule().get(2).size());
		assertEquals(new Long(3), this.jobService.getResultingSchedule().get(2).get(0));
		assertEquals(new Long(6), this.jobService.getResultingSchedule().get(2).get(1));
	}	
	
	@Test
	/**
	 * Tests a organization of all from the `scheduling-jobs-product` JSON file starting on Janury 10th at midday.
	 */
	public void organizeProductSchedulingJobsStartingOnJan10thAtMidday() {
		this.jobService.getAllJobs("./src/test/resources/input/scheduling-jobs-product.json");
		this.jobService.saveJobs("./src/test/resources/output/scheduling-jobs-product.json");
		assertTrue(this.jobService.compareJobs("./src/test/resources/input/scheduling-jobs.json", 
				"./src/test/resources/output/scheduling-jobs.json"));
		
		this.jobService.organizeSchedulingJobs(LocalDateTime.of(2020, 1, 10, 12, 00, 00),
		    LocalDateTime.of(2020, 1, 12, 18, 00, 00));
		
		assertNotNull(this.jobService.getResultingSchedule());
		assertEquals(3, this.jobService.getResultingSchedule().size());
		assertEquals(3, this.jobService.getResultingSchedule().get(0).size());
		assertEquals(new Long(4), this.jobService.getResultingSchedule().get(0).get(0));
		assertEquals(new Long(5), this.jobService.getResultingSchedule().get(0).get(1));
		assertEquals(new Long(7), this.jobService.getResultingSchedule().get(0).get(2));
		assertEquals(1, this.jobService.getResultingSchedule().get(1).size());
		assertEquals(new Long(2), this.jobService.getResultingSchedule().get(1).get(0));
		assertEquals(2, this.jobService.getResultingSchedule().get(2).size());
		assertEquals(new Long(3), this.jobService.getResultingSchedule().get(2).get(0));
		assertEquals(new Long(6), this.jobService.getResultingSchedule().get(2).get(1));
	}	
	
	@Test
	/**
	 * Tests a organization of all from the `scheduling-jobs-product` JSON file starting on January 11th at eight o'clock.
	 */
	public void organizeProductSchedulingJobsStartingOnJan11thAtEightOClock() {
		this.jobService.getAllJobs("./src/test/resources/input/scheduling-jobs-product.json");
		this.jobService.saveJobs("./src/test/resources/output/scheduling-jobs-product.json");
		assertTrue(this.jobService.compareJobs("./src/test/resources/input/scheduling-jobs.json", 
				"./src/test/resources/output/scheduling-jobs.json"));
		
		this.jobService.organizeSchedulingJobs(LocalDateTime.of(2020, 1, 11, 8, 00, 00),
		    LocalDateTime.of(2020, 1, 12, 18, 00, 00));
		
		assertNotNull(this.jobService.getResultingSchedule());
		assertEquals(2, this.jobService.getResultingSchedule().get(0).size());
		assertEquals(new Long(7), this.jobService.getResultingSchedule().get(0).get(0));
		assertEquals(new Long(2), this.jobService.getResultingSchedule().get(0).get(1));
		assertEquals(2, this.jobService.getResultingSchedule().get(1).size());
		assertEquals(new Long(3), this.jobService.getResultingSchedule().get(1).get(0));
		assertEquals(new Long(6), this.jobService.getResultingSchedule().get(1).get(1));
	}	
	
	@Test
	/**
	 * Tests a organization of all jobs from the `scheduling-jobs-product` JSON file finishing on January 12th at ten o´clock.
	 */
	public void organizeProductSchedulingJobsFinishingOnJanury12thAtTenOClock() {
		this.jobService.getAllJobs("./src/test/resources/input/scheduling-jobs-product.json");
		this.jobService.saveJobs("./src/test/resources/output/scheduling-jobs-product.json");
		assertTrue(this.jobService.compareJobs("./src/test/resources/input/scheduling-jobs.json", 
				"./src/test/resources/output/scheduling-jobs.json"));
		
		this.jobService.organizeSchedulingJobs(LocalDateTime.of(2020, 1, 10, 8, 00, 00),
		    LocalDateTime.of(2020, 1, 12, 10, 00, 00));
		
		assertNotNull(this.jobService.getResultingSchedule());
		assertEquals(3, this.jobService.getResultingSchedule().size());
		assertEquals(3, this.jobService.getResultingSchedule().get(0).size());
		assertEquals(new Long(1), this.jobService.getResultingSchedule().get(0).get(0));
		assertEquals(new Long(4), this.jobService.getResultingSchedule().get(0).get(1));
		assertEquals(new Long(5), this.jobService.getResultingSchedule().get(0).get(2));
		assertEquals(2, this.jobService.getResultingSchedule().get(1).size());
		assertEquals(new Long(7), this.jobService.getResultingSchedule().get(1).get(0));
		assertEquals(new Long(2), this.jobService.getResultingSchedule().get(1).get(1));
		assertEquals(1, this.jobService.getResultingSchedule().get(2).size());
		assertEquals(new Long(3), this.jobService.getResultingSchedule().get(2).get(0));
	}	
	
	@Test
	/**
	 * Tests a organization of all jobs from the `scheduling-jobs-product` JSON file finishing on January 11th at eighteen o´clock.
	 */
	public void organizeProductSchedulingJobsFinishingOnJanury11thAtEighteenOClock() {
		this.jobService.getAllJobs("./src/test/resources/input/scheduling-jobs-product.json");
		this.jobService.saveJobs("./src/test/resources/output/scheduling-jobs-product.json");
		assertTrue(this.jobService.compareJobs("./src/test/resources/input/scheduling-jobs.json", 
				"./src/test/resources/output/scheduling-jobs.json"));
		
		this.jobService.organizeSchedulingJobs(LocalDateTime.of(2020, 1, 10, 8, 00, 00),
		    LocalDateTime.of(2020, 1, 11, 18, 00, 00));
		
		assertNotNull(this.jobService.getResultingSchedule());
		assertEquals(2, this.jobService.getResultingSchedule().size());
		assertEquals(3, this.jobService.getResultingSchedule().get(0).size());
		assertEquals(new Long(1), this.jobService.getResultingSchedule().get(0).get(0));
		assertEquals(new Long(4), this.jobService.getResultingSchedule().get(0).get(1));
		assertEquals(new Long(5), this.jobService.getResultingSchedule().get(0).get(2));
		assertEquals(2, this.jobService.getResultingSchedule().get(1).size());
		assertEquals(new Long(7), this.jobService.getResultingSchedule().get(1).get(0));
		assertEquals(new Long(2), this.jobService.getResultingSchedule().get(1).get(1));
	}	
}
