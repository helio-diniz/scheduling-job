# Changelog

## [1.0.2] - 2020-01-08 

### Added
- New `Job` class to implement a model for a job.
- New `SchedulingJobs` class to implement a repository for scheduling.
- New `JobTest` test class with test cases below: 
  - Job model test cases: `createAJob`, `createAJobWithoutDeadline` and `createAJobWithAInvalidDeadlineText`. 
  - Job list test cases: `createAEmptyJobList`, `createAJobListWithJustOneJob`,  `createAJobListWithThreeJobs` and `createASortedJobListWithThreeJobs`.
  - Schedule organization: `organizeASchedulingJobListWithThreeJobs`, `organizeASchedulingJobListWithThreeJobsStartingElevenOClock`, `organizeASchedulingJobListWithThreeJobsFinishingEightOClock`, `organizeASchedulingJobListWithThreeJobsWithInvalidPeriod` and `organizeAEmptySchedulingJobList`.
  - Branch: `job_list`.
  

## [1.0.1] - 2020-01-07

### Added
- New `JSONUtils` class for reading, writing and comparing JSON files.
- New `Employee`  class to test object conversion.
- New `JSONFileTest` test class with the `readingASimpleJSONFile`, `readingOneObjectListJSONFile`, `readingTwoObjectListJSONFile`, `readingAEmptyJSONFile`, `readingAnInexistingJSONFile`, `writingASingleEmployeeJSONFile`, `writingAThreeEmployeeListJSONFile`, `writingANullObjectJSONFile` and `writingAEmptyEmployeeListJSONFile` test cases. 
- New input test resouce JSON files for tests' reference.
- New change log.
- Branch: `json_file`.


## [1.0.0] 2020-01-06
### Added
- New maven project.  
- Branch: `creating_java_project`.