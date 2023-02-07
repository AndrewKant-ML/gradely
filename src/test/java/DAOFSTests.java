import it.uniroma2.dicii.ispw.gradely.CSVParser;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DAOFSTests {

    @Test
    void testCsvReader() throws Exception {
        CSVParser parser = new CSVParser();
        List<String[]> content = parser.readAllLines("user");
        for (String[] s : content) {
            for (String value : s) {
                System.out.println(value);
            }
        }
    }
}
