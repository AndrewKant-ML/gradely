package it.uniroma2.dicii.ispw.gradely;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVParser {

    public List<String[]> readAllLines(String filename) throws Exception {

        if (!filename.endsWith(".csv")) filename = filename.concat(".csv");
        URL url = getClass().getClassLoader().getResource("/csv/" + filename);
        try (Reader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/csv/" + filename)))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }
}
