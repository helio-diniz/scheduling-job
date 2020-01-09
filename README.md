# scheduling-job

A component that organizes a scheduling job list read from a JSON file.

---

## Design

The design of this project was made applying the Test-Driven Development concepts. It started with the `JSONUtils` class, creating tests and elaborating the design through `Employee` and `JSONFileTest`. After that, the `JobTest` and `JobServiceTest` were used to implements the `Job`, `SchedulingJobs` and `JobService` classes.

1. The `JSONUtils` class is a tool for reading, writing and comparing JSON Object stored in files.
2. The `Job` class represents a model for a job.
3. The `SchedulingJobs` class is a repository for jobs.
4. The `JobService` class implements the service of loading, storing and organizing jobs.
5. All JSON files used by the test classes as data input are in the `/src/test/resources/input/` folder.
6. Some JSON files generated to be compared to test reference files are in the `/src/test/resources/output/` folder.

---

## Instructions

In addition to the unit tests, there is also a Java `Application` class to read the jobs information from a JSON file and organize it in a schedule as proposed. The result is printed in a text format when you run the application. To run the application in the Eclipse, it is necessary to:
1. Open the `Application` class in the package "src/main/java" folder.
2. Run the class as a Java Application. 
3. The scheduling jobs will be read from the `scheduling-jobs.json` file in the `/src/main/resources/input/` folder and its contents will be printed in the console.
4. The jobs will be organized and the resulting schedule will be printed in the console.

---

## Application result

Massa de Dados
[
  {
    "id": 1,
    "description": "Importação de arquivos de fundos",
    "deadlineText": "2019-11-10 12:00:00",
    "duration": 2
  },
  {
    "id": 3,
    "description": "Importação de dados de integração",
    "deadlineText": "2019-11-11 08:00:00",
    "duration": 6
  },
  {
    "id": 2,
    "description": "Importação de dados da Base Legada",
    "deadlineText": "2019-11-11 12:00:00",
    "duration": 4
  }
]

Output esperado
[[1, 3], [2]]
