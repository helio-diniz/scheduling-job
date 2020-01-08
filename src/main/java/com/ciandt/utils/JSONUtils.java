package com.ciandt.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

/**
 * This class implements an utility tool for reading, wrting and comparing JSON files.
 * 
 * @author Helio Diniz
 * @version 1.0
 */
public class JSONUtils {
	
	/**
	 * Default constructor.
	 */
	public JSONUtils() {
		super();
	}

	/**
	 * Reads data from a JSON file.
	 * 
	 * @param filename
	 *        the full path name of a JSON file.
	 * @return an object read from the file.
	 * @throws IOException
	 *         When the file can´t be read.
	 * @throws ParseException
	 *         When the data file can´t be parsed to a JSON file.
	 */
	public Object read(String filename) throws IOException, ParseException{
		JSONParser parser = new JSONParser();		
		Object object = parser.parse(new FileReader(filename));
		
		return object;
	}

	/**
	 * Writes an objetct to JSON file.
	 *  
	 * @param object
	 *        the object to be saved.
	 * @param filename
	 *        the full path name of a JSON file.
	 * @throws IOException
	 *         When the file can´t be wrritten.
	 */
	public void write(Object object, String filename) throws IOException {
	    Gson gson = new Gson();
	    String jsonString = "{}";
	    if (object != null){	    	
	    	jsonString = gson.toJson(object);
	    }
	    
        FileWriter writer = new FileWriter(filename);
        writer.write(jsonString);
        writer.close();
	}
	
	/**
	 * Compares two JSON files.
	 * 
	 * @param inputFilename
	 *  	  the full path name of the input JSON file.
	 * @param outputFilename
	 *  	  the full path name of the output JSON file.
	 * @return
	 */
	public boolean compare(String inputFilename, String outputFilename) {
		boolean result;
		try {
			Object objectJsonIn = this.read(inputFilename);
			Object objectJsonOut = this.read(outputFilename);
			result = objectJsonIn.equals(objectJsonOut); 
		} catch (Exception e) {
			result = false;
		} 
		return result;
	}

}
