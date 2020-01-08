/**
 * 
 */
package com.ciandt.json;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.ciandt.model.Job;
import com.ciandt.repository.SchedulingJobs;

/**
 * Tests the job model and the job repository.
 * 
 * @author Helio Diniz
 * @version 1.0
 */
public class JobTest {

	@Test
	/**
	 * Tests a simple job creation.
	 */
	public void createAJob() {
		Job job = new Job(1, "Importação de arquivos do fundo", "2019-11-10 09:00:00", 2L);
		assertEquals(new Integer(1), job.getId());
		assertEquals("Importação de arquivos do fundo", job.getDescription());
		assertEquals(LocalDateTime.of(2019, 11, 10, 9, 0, 0), job.getDeadline());
		assertEquals("2019-11-10 09:00:00", job.getDeadlineText());
		assertEquals(new Long(2), job.getDuration());
	}

	@Test
	/**
	 * Tests a job creation without a deadline.
	 */
	public void createAJobWithoutDeadline() {
		Job job = new Job(1, "Importação de arquivos do fundo", "", 2L);
		assertEquals(new Integer(1), job.getId());
		assertEquals("Importação de arquivos do fundo", job.getDescription());
		assertNull(job.getDeadline());
		assertEquals("", job.getDeadlineText());
		assertEquals(new Long(2), job.getDuration());
	}

	@Test
	/**
	 * Tests a job creation with a invalid deadline.
	 */
	public void createAJobWithAInvalidDeadlineText() {
		Job job = new Job(1, "Importação de arquivos do fundo", "2019-xx-xx", 2L);
		assertEquals(new Integer(1), job.getId());
		assertEquals("Importação de arquivos do fundo", job.getDescription());
		assertNull(job.getDeadline());
		assertEquals("", job.getDeadlineText());
		assertEquals(new Long(2), job.getDuration());
	}

	@Test
	/**
	 * Tests an empty job list creation.
	 */
	public void createAEmptyJobList() {
		SchedulingJobs jobs = new SchedulingJobs();
		assertEquals(0, jobs.size());
	}

	@Test
	/**
	 * Tests a job list creation with just one job.
	 */
	public void createAJobListWithJustOneJob() {
		SchedulingJobs jobs = new SchedulingJobs();
		jobs.add(new Job(1, "Importação de arquivos do fundo", "2019-11-10 09:00:00", 2L));
		assertEquals(1, jobs.size());

		assertEquals(new Integer(1), jobs.get(0).getId());
		assertEquals("Importação de arquivos do fundo", jobs.get(0).getDescription());
		assertEquals(LocalDateTime.of(2019, 11, 10, 9, 0, 0), jobs.get(0).getDeadline());
		assertEquals("2019-11-10 09:00:00", jobs.get(0).getDeadlineText());
		assertEquals(new Long(2), jobs.get(0).getDuration());
	}

	@Test
	/**
	 * Tests a job list creation with three jobs.
	 */
	public void cresateAJobListWithThreeJobs() {
		SchedulingJobs jobs = new SchedulingJobs();
		jobs.add(new Job(1, "Importação de arquivos do fundo", "2019-11-10 09:00:00", 2L));
		jobs.add(new Job(2, "Importação de dados da Base Legada", "2019-11-11 12:00:00", 4L));
		jobs.add(new Job(3, "Importação de dados de integração", "2019-11-11 08:00:00", 6L));
		assertEquals(3, jobs.size());

		assertEquals(new Integer(1), jobs.get(0).getId());
		assertEquals("Importação de arquivos do fundo", jobs.get(0).getDescription());
		assertEquals(LocalDateTime.of(2019, 11, 10, 9, 0, 0), jobs.get(0).getDeadline());
		assertEquals("2019-11-10 09:00:00", jobs.get(0).getDeadlineText());
		assertEquals(new Long(2), jobs.get(0).getDuration());

		assertEquals(new Integer(2), jobs.get(1).getId());
		assertEquals("Importação de dados da Base Legada", jobs.get(1).getDescription());
		assertEquals(LocalDateTime.of(2019, 11, 11, 12, 0, 0), jobs.get(1).getDeadline());
		assertEquals("2019-11-11 12:00:00", jobs.get(1).getDeadlineText());
		assertEquals(new Long(4), jobs.get(1).getDuration());

		assertEquals(new Integer(3), jobs.get(2).getId());
		assertEquals("Importação de dados de integração", jobs.get(2).getDescription());
		assertEquals(LocalDateTime.of(2019, 11, 11, 8, 0, 0), jobs.get(2).getDeadline());
		assertEquals("2019-11-11 08:00:00", jobs.get(2).getDeadlineText());
		assertEquals(new Long(6), jobs.get(2).getDuration());
	}

	@Test
	/**
	 * Tests the creation and the sorting of a job list with three jobs.
	 */
	public void createASortedJobListWithThreeJobs() {
		SchedulingJobs jobs = new SchedulingJobs();
		jobs.add(new Job(1, "Importação de arquivos do fundo", "2019-11-10 09:00:00", 2L));
		jobs.add(new Job(2, "Importação de dados da Base Legada", "2019-11-11 12:00:00", 4L));
		jobs.add(new Job(3, "Importação de dados de integração", "2019-11-11 08:00:00", 6L));
		assertEquals(3, jobs.size());

		jobs.sort();

		assertEquals(new Integer(1), jobs.get(0).getId());
		assertEquals("Importação de arquivos do fundo", jobs.get(0).getDescription());
		assertEquals(LocalDateTime.of(2019, 11, 10, 9, 0, 0), jobs.get(0).getDeadline());
		assertEquals("2019-11-10 09:00:00", jobs.get(0).getDeadlineText());
		assertEquals(new Long(2), jobs.get(0).getDuration());

		assertEquals(new Integer(3), jobs.get(1).getId());
		assertEquals("Importação de dados de integração", jobs.get(1).getDescription());
		assertEquals(LocalDateTime.of(2019, 11, 11, 8, 0, 0), jobs.get(1).getDeadline());
		assertEquals("2019-11-11 08:00:00", jobs.get(1).getDeadlineText());
		assertEquals(new Long(6), jobs.get(1).getDuration());

		assertEquals(new Integer(2), jobs.get(2).getId());
		assertEquals("Importação de dados da Base Legada", jobs.get(2).getDescription());
		assertEquals(LocalDateTime.of(2019, 11, 11, 12, 0, 0), jobs.get(2).getDeadline());
		assertEquals("2019-11-11 12:00:00", jobs.get(2).getDeadlineText());
		assertEquals(new Long(4), jobs.get(2).getDuration());
	}

	@Test
	/**
	 * Tests the organization of a job schedule with three jobs.
	 */
	public void organizeASchedulingJobListWithThreeJobs() {
		SchedulingJobs jobs = new SchedulingJobs();
		jobs.add(new Job(1, "Importação de arquivos do fundo", "2019-11-10 12:00:00", 2L));
		jobs.add(new Job(2, "Importação de dados da Base Legada", "2019-11-11 12:00:00", 4L));
		jobs.add(new Job(3, "Importação de dados de integração", "2019-11-11 08:00:00", 6L));
		assertEquals(3, jobs.size());

		List<List<Integer>> schedule = jobs.organize(LocalDateTime.of(2019, 11, 10, 9, 00, 00),
		    LocalDateTime.of(2019, 11, 11, 12, 00, 00));

		assertNotNull(schedule);
		assertEquals(2, schedule.size());
		assertEquals(2, schedule.get(0).size());
		assertEquals(new Integer(1), schedule.get(0).get(0));
		assertEquals(new Integer(3), schedule.get(0).get(1));
		assertEquals(1, schedule.get(1).size());
		assertEquals(new Integer(2), schedule.get(1).get(0));
	}

	@Test
	/**
	 * Tests the organization of a job schedule with three jobs and starting at 11
	 * o´clock.
	 */
	public void organizeASchedulingJobListWithThreeJobsStartingElevenOClock() {
		SchedulingJobs jobs = new SchedulingJobs();
		jobs.add(new Job(1, "Importação de arquivos do fundo", "2019-11-10 12:00:00", 2L));
		jobs.add(new Job(2, "Importação de dados da Base Legada", "2019-11-11 12:00:00", 4L));
		jobs.add(new Job(3, "Importação de dados de integração", "2019-11-11 08:00:00", 6L));
		assertEquals(3, jobs.size());

		List<List<Integer>> schedule = jobs.organize(LocalDateTime.of(2019, 11, 10, 11, 00, 00),
		    LocalDateTime.of(2019, 11, 11, 12, 00, 00));

		assertNotNull(schedule);
		assertEquals(2, schedule.size());
		assertEquals(1, schedule.get(0).size());
		assertEquals(new Integer(3), schedule.get(0).get(0));
		assertEquals(1, schedule.get(1).size());
		assertEquals(new Integer(2), schedule.get(1).get(0));
	}

	@Test
	/**
	 * Tests the organization of a job schedule with three jobs and finishing at
	 * eight o´clock.
	 */
	public void organizeASchedulingJobListWithThreeJobsFinishingEightOClock() {
		SchedulingJobs jobs = new SchedulingJobs();
		jobs.add(new Job(1, "Importação de arquivos do fundo", "2019-11-10 12:00:00", 2L));
		jobs.add(new Job(2, "Importação de dados da Base Legada", "2019-11-11 12:00:00", 4L));
		jobs.add(new Job(3, "Importação de dados de integração", "2019-11-11 08:00:00", 6L));
		assertEquals(3, jobs.size());

		List<List<Integer>> schedule = jobs.organize(LocalDateTime.of(2019, 11, 10, 9, 00, 00),
		    LocalDateTime.of(2019, 11, 11, 8, 00, 00));

		assertNotNull(schedule);
		assertEquals(1, schedule.size());
		assertEquals(2, schedule.get(0).size());
		assertEquals(new Integer(1), schedule.get(0).get(0));
		assertEquals(new Integer(3), schedule.get(0).get(1));
	}

	@Test
	/**
	 * Tests the organization of a job schedule with three jobs in a invalid
	 * period.
	 */
	public void organizeASchedulingJobListWithThreeJobsWithInvalidPeriod() {
		SchedulingJobs jobs = new SchedulingJobs();
		jobs.add(new Job(1, "Importação de arquivos do fundo", "2019-11-10 12:00:00", 2L));
		jobs.add(new Job(2, "Importação de dados da Base Legada", "2019-11-11 12:00:00", 4L));
		jobs.add(new Job(3, "Importação de dados de integração", "2019-11-11 08:00:00", 6L));
		assertEquals(3, jobs.size());

		List<List<Integer>> schedule = jobs.organize(LocalDateTime.of(2019, 11, 12, 9, 00, 00),
		    LocalDateTime.of(2019, 11, 13, 8, 00, 00));

		assertNotNull(schedule);
		assertEquals(0, schedule.size());
	}

	@Test
	/**
	 * Tests the organization of a job schedule with three jobs in a invalid
	 * period.
	 */
	public void organizeAEmptySchedulingJobList() {
		SchedulingJobs jobs = new SchedulingJobs();
		assertEquals(0, jobs.size());
		List<List<Integer>> schedule = jobs.organize(LocalDateTime.of(2019, 11, 10, 9, 00, 00),
		    LocalDateTime.of(2019, 11, 11, 8, 00, 00));
		assertNotNull(schedule);
		assertEquals(0, schedule.size());
	}

}
