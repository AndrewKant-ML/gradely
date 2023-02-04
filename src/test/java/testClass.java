import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import org.junit.jupiter.api.Test;

public class testClass {

    @Test
    public void test() {
        DegreeCourseCodeEnum code = DegreeCourseCodeEnum.LM01;
        assert DegreeCourseCodeEnum.getDegreeCourseCodeByValue(5).equals(code);
    }
}
