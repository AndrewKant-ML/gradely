import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DAOFSTests {

    @Test
    void testProfessorDAO() throws Exception {
        Professor professor = DAOFactoryAbstract.getInstance().getProfessorDAO().getProfessorByUser(
                UserLazyFactory.getInstance().getUserByCodiceFiscale("FLX")
        );
        Assertions.assertNotNull(professor);
    }
}
