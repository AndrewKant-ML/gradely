package it.uniroma2.dicii.ispw.gradely;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public List<String[]> readAllLines(String filename) throws ResourceNotFoundException, IOException, CsvException {
        try (Reader reader = buildResourceReader(buildResourcePath(filename))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

    public List<String[]> findByColumnValue(String filename, String columnName, String value) throws Exception {
        List<String[]> list = new ArrayList<>();
        try (Reader reader = buildResourceReader(buildResourcePath(filename))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
        }
        return list;
    }

    private String buildResourcePath(String filename) {
        if (!filename.endsWith(".csv"))
            filename = filename.concat(".csv");
        return String.format("/csv/%s", filename);
    }

    private BufferedReader buildResourceReader(String resource) throws ResourceNotFoundException {
        InputStream stream = getClass().getResourceAsStream(resource);
        if (stream == null)
            throw new ResourceNotFoundException(ExceptionMessagesEnum.RESOURCE_NOT_FOUND.message);
        return new BufferedReader(new InputStreamReader(stream));
    }
}
