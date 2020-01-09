package com.ciandt.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class implements an utility tool for reading, wrting and comparing JSON
 * files.
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
   *         When the file can't be read.
   * @throws ParseException
   *         When the data file can't be parsed to a JSON file.
   */
  public Object read(String filename) throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    Object object = parser.parse(this.openReadingFile(filename));

    return object;
  }

  /**
   * @param filename
   *        the file name.
   * @return the file buffer to be read.
   * @throws IOException 
   *         When the file can't be read.
   */
  private BufferedReader openReadingFile(String filename) throws IOException {

    BufferedReader fileBuffer = new BufferedReader(new InputStreamReader(
      new FileInputStream(filename), "UTF-8"));
 
    return fileBuffer;
  }

  /**
   * Writes an objetct to JSON file.
   * 
   * @param object
   *        the object to be saved.
   * @param filename
   *        the full path name of a JSON file.
   * @throws IOException
   *         When the file can't be wrritten.
   */
  public void write(Object object, String filename) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String jsonString = "{}";
    if (object != null) {
      jsonString = gson.toJson(object);
    }

    BufferedWriter writer = openWritingFile(filename);
    writer.write(jsonString);
    writer.close();
  }
  
  /**
   * Open a file to be written.
   * 
   * @param filename
   *        the file name.
   * @return the file buffer to be written.
   * 
   * @throws FileNotFoundException 
   *         When the file is not found.
   * @throws UnsupportedEncodingException
   *         When the enconding is not supported. 
   * @throws IOException 
   *         When the file can't be wrritten..
   */
  private BufferedWriter openWritingFile(String filename) throws UnsupportedEncodingException, FileNotFoundException {

    BufferedWriter fileBuffer = new BufferedWriter(new OutputStreamWriter(
      new FileOutputStream(filename), "UTF-8"));
 
    return fileBuffer;
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

  /**
   * Converts a object to a JSON string.
   * 
   * @param object
   *        the object to be converted.
   *        
   * @return the converted JSON string.      
   */
  public String toString(Object object) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String jsonString = "{}";
    if (object != null) {
      jsonString = gson.toJson(object);
    }
    return jsonString;
  }
}
