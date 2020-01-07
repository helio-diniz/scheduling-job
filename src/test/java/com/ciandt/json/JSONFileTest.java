package com.ciandt.json;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import com.ciandt.utils.JSONUtils;

/**
 * Tests the JSON files handling.
 * 
 * @author Helio Diniz
 * @version 1.0
 */
public class JSONFileTest {
	private JSONUtils jsonUtils;
	
	@Before
	/**
	 * Initializes the test cases.
	 * 
	 * @throws Exception 
	 *         On generic error.
	 */
	public void setUp() throws Exception {
		jsonUtils = new JSONUtils();
	}

	/**
	 * Tests a simple JSON file reading.
	 * 
	 * @throws IOException
	 *         When the JSON file can´t be read. 
	 * @throws ParseException
	 *         Whe the data from the JSON file can´t be parsed.
	 */
	@Test
	public void readingASimpleJSONFile() throws IOException, ParseException {
		JSONObject jsonObject = (JSONObject) jsonUtils.read("./src/test/resources/input/simple-file.json");
		assertNotNull(jsonObject);
		assertEquals("João", jsonObject.get("name"));
		assertEquals("Silva", jsonObject.get("surname"));
		assertEquals("Brasil", jsonObject.get("country"));
		assertEquals("SP", jsonObject.get("state"));
	}
	
	/**
	 * Tests a one-object list JSON file reading.
	 * 
	 * @throws IOException
	 *         When the JSON file can´t be read. 
	 * @throws ParseException
	 *         Whe the data from the JSON file can´t be parsed.
	 */
	@Test
	public void readingOneObjectListJSONFile() throws IOException, ParseException {
		JSONArray jsonArray = (JSONArray) jsonUtils.read("./src/test/resources/input/one-object-list-file.json");
		
		assertNotNull(jsonArray);
		assertEquals(1, jsonArray.size());
		
		JSONObject jsonObject = (JSONObject) jsonArray.get(0);	
		assertEquals("João", jsonObject.get("name"));
		assertEquals("Silva", jsonObject.get("surname"));
		assertEquals("Brasil", jsonObject.get("country"));
		assertEquals("SP", jsonObject.get("state"));
	}
	
	/**
	 * Tests a two-object list JSON file reading.
	 * 
	 * @throws IOException
	 *         When the JSON file can´t be read. 
	 * @throws ParseException
	 *         Whe the data from the JSON file can´t be parsed.
	 */
	@Test
	public void readingTwoObjectListJSONFile() throws IOException, ParseException {
		JSONArray jsonArray = (JSONArray) jsonUtils.read("./src/test/resources/input/two-object-list-file.json");
		
		assertNotNull(jsonArray);
		assertEquals(2, jsonArray.size());
		
		JSONObject jsonObject = (JSONObject) jsonArray.get(0);	
		assertEquals("João", jsonObject.get("name"));
		assertEquals("Silva", jsonObject.get("surname"));
		assertEquals("Brasil", jsonObject.get("country"));
		assertEquals("SP", jsonObject.get("state"));
		
		jsonObject = (JSONObject) jsonArray.get(1);	
		assertEquals("Lúcia", jsonObject.get("name"));
		assertEquals("Martins", jsonObject.get("surname"));
		assertEquals("Brasil", jsonObject.get("country"));
		assertEquals("PR", jsonObject.get("state"));
	}
	
	/**
	 * Tests a empty JSON file reading.
	 * 
	 * @throws IOException
	 *         When the JSON file can´t be read. 
	 * @throws ParseException
	 *         Whe the data from the JSON file can´t be parsed.
	 */
	@Test(expected = ParseException.class)
	public void readingAEmptyJSONFile() throws IOException, ParseException {
		JSONObject jsonObject = (JSONObject) jsonUtils.read("./src/test/resources/input/empty-file.json");
		assertNotNull(jsonObject);
	}

	/**
	 * Tests an inexisting JSON file reading.
	 * 
	 * @throws IOException
	 *         When the JSON file can´t be read. 
	 * @throws ParseException
	 *         Whe the data from the JSON file can´t be parsed.
	 */
	@Test(expected = IOException.class)
	public void readingAnInexistingJSONFile() throws IOException, ParseException {
		JSONObject jsonObject = (JSONObject) jsonUtils.read("./src/test/resources/input/inexisting-file.json");
		assertNotNull(jsonObject);
	}
	
	/**
	 * Tests a single employee JSON file writing.
	 * 
	 * @throws IOException
	 *         When the JSON file can´t be written. 
	 * @throws ParseException
	 *         Whe the data from the JSON file can´t be parsed.
	 */
	@Test
	public void writingASingleEmployeeJSONFile() throws IOException, ParseException {
		Employee employee = new Employee(1, "Marcos", "Silvério", LocalDate.of(1974, 12, 02) , 12567.89, true);
	    
		jsonUtils.write(employee, "./src/test/resources/output/single-employee.json");
		
		assertTrue(jsonUtils.compare("./src/test/resources/input/single-employee.json", 
			"./src/test/resources/output/single-employee.json"));
	}
	
	/**
	 * Tests a three-employee list JSON file writing.
	 * 
	 * @throws IOException
	 *         When the JSON file can´t be written. 
	 * @throws ParseException
	 *         Whe the data from the JSON file can´t be parsed.
	 */
	@Test
	public void writingAThreeEmployeeListJSONFile() throws IOException, ParseException {
		List<Employee> employees = new ArrayList<Employee>();
		
		employees.add(new Employee(1, "Marcos", "Silvério", LocalDate.of (1974, 12, 02), 12567.89, true));
		employees.add(new Employee(2, "Ludmila", "Amaral", LocalDate.of(1987, 07, 15), 7890.77, true));
		employees.add(new Employee(3, "Francisco", "Moreira", LocalDate.of(2011, 03, 21), 1670.93, false));
	    
		jsonUtils.write(employees, "./src/test/resources/output/three-employee-list.json");
		
		assertTrue(jsonUtils.compare("./src/test/resources/input/three-employee-list.json", 
			"./src/test/resources/output/three-employee-list.json"));
	}
	
	/**
	 * Tests a null-employee JSON file writing.
	 * 
	 * @throws IOException
	 *         When the JSON file can´t be written. 
	 * @throws ParseException
	 *         Whe the data from the JSON file can´t be parsed.
	 */
	@Test
	public void writingANullObjectJSONFile() throws IOException, ParseException {
		Employee employee = null;
	    
		jsonUtils.write(employee, "./src/test/resources/output/null-employee.json");
		
		assertTrue(jsonUtils.compare("./src/test/resources/input/null-employee.json", 
			"./src/test/resources/output/null-employee.json"));
	}
	
	/**
	 * Tests an empty employee list JSON file writing.
	 * 
	 * @throws IOException
	 *         When the JSON file can´t be written. 
	 * @throws ParseException
	 *         Whe the data from the JSON file can´t be parsed.
	 */
	@Test
	public void writingAEmptyEmployeeListJSONFile() throws IOException, ParseException {
		List<Employee> employees = new ArrayList<Employee>();
	    
		jsonUtils.write(employees, "./src/test/resources/output/empty-employee-list.json");
		
		assertTrue(jsonUtils.compare("./src/test/resources/input/empty-employee-list.json", 
			"./src/test/resources/output/empty-employee-list.json"));
	}
}
