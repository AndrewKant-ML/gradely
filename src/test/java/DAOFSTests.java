import it.uniroma2.dicii.ispw.gradely.CSVParser;
import org.junit.jupiter.api.Test;

public class DAOFSTests {

    @Test
    void testCsvReader() throws Exception {
        CSVParser parser = new CSVParser();
        parser.readAllLines("test");
    }
}
