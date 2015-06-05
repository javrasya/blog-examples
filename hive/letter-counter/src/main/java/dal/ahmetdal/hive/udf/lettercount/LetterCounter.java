package dal.ahmetdal.hive.udf.lettercount;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public final class LetterCounter extends UDF {

    public Integer evaluate(final Text input) {
        if (input == null) return null;
        return input.toString().length();
    }

}