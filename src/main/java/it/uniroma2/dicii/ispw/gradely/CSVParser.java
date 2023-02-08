package it.uniroma2.dicii.ispw.gradely;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    /**
     * Reads all the content of a given file
     *
     * @param filename the file name
     * @return a List of Strings arrays, each representing a row of the CSV file
     * @throws ResourceNotFoundException thrown if the file cannot be found
     * @throws DAOException              thrown if errors occur while reading file content
     */
    public List<List<String>> readAllLines(String filename) throws ResourceNotFoundException, DAOException, CsvException {
        try (Reader reader = buildResourceReader(filename)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                List<List<String>> fileContent = new ArrayList<>();
                String[] nextLine;
                while ((nextLine = csvReader.readNext()) != null) {
                    fileContent.add(List.of(nextLine));
                }
                return fileContent;
            }
        } catch (CsvException | IOException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    /**
     * Build the path to a given CSV resource, by adding the file directory
     * (/csv/) and the .csv suffix if not present
     *
     * @param filename the name of the resource
     * @return the resource path
     */
    private String buildResourcePath(String filename) {
        if (!filename.endsWith(".csv"))
            filename = filename.concat(".csv");
        return String.format("/csv/%s", filename);
    }

    /**
     * Builds a BufferedReader for a given resource
     *
     * @param resource the resource references to by the reader
     * @return a BufferedReader instance to the resource
     * @throws ResourceNotFoundException if the given resource cannot be found
     */
    public BufferedReader buildResourceReader(String resource) throws ResourceNotFoundException {
        InputStream stream = getClass().getResourceAsStream(buildResourcePath(resource));
        if (stream == null)
            throw new ResourceNotFoundException(ExceptionMessagesEnum.RESOURCE_NOT_FOUND.message);
        return new BufferedReader(new InputStreamReader(stream));
    }
}
