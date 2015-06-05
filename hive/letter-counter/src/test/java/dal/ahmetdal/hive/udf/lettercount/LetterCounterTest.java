package dal.ahmetdal.hive.udf.lettercount;

import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;

public class LetterCounterTest {

    @Test
    public void testUDF() throws MalformedURLException {
        LetterCounter letterCounter = new LetterCounter();
        String inputString = "TestInput";
        Assert.assertEquals(new Integer(9), letterCounter.evaluate(new Text(inputString)));
    }

}
