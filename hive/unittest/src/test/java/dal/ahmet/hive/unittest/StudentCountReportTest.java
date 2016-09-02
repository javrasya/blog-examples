package dal.ahmet.hive.unittest;

import com.klarna.hiverunner.HiveShell;
import com.klarna.hiverunner.StandaloneHiveRunner;
import com.klarna.hiverunner.annotations.HiveSQL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


/**
 * Created by ahmetdal on 02/09/16.
 */


@RunWith(StandaloneHiveRunner.class)
public class StudentCountReportTest {

    private String database = "mydatabase";

    @HiveSQL(files = {})
    private HiveShell hiveShell;


    @Before
    public void setUp() throws FileNotFoundException {


        // Create the database
        hiveShell.execute("CREATE DATABASE IF NOT EXISTS " + database + ";");


        // Create Tables
        hiveShell.execute(getResourceFile("hive/create/school.hql"));
        hiveShell.execute(getResourceFile("hive/create/student.hql"));
        hiveShell.execute(getResourceFile("hive/create/student_count_report.hql"));
    }

    private void insertInto(String table, String resourcePath) {
        String delimiter = ",";
        String nullValue = "\\N";

        hiveShell.insertInto(database, table)
                .withAllColumns()
                .addRowsFromDelimited(
                        new File(
                                getClass()
                                        .getClassLoader()
                                        .getResource(resourcePath)
                                        .getPath()
                        ),
                        delimiter,
                        nullValue
                )
                .commit();
    }

    private File getResourceFile(String resourcePath) throws FileNotFoundException {
        URL url = getClass().getClassLoader().getResource(resourcePath);
        if (null == url) {
            throw new FileNotFoundException(resourcePath);
        }
        return new File(url.getPath());
    }


    @Test
    public void testSuccessCase1() throws FileNotFoundException {

        // Insert test data
        insertInto("school", "StudentCountReportTest/successCase1/school.csv");
        insertInto("student", "StudentCountReportTest/successCase1/student.csv");

        // Execute the report
        hiveShell.execute(getResourceFile("hive/report/execute_student_count_report.hql"));


        // Check and assert the result
        List<Object[]> actualResultSet = hiveShell.executeStatement("select * from student_count_report order by school_name");


        List<Object[]> expectedResultSet = Arrays.asList(
                new Object[]{"Atatürk Lisesi", 2L},
                new Object[]{"Cumhuriyet İlköğretim Okulu", 5L},
                new Object[]{"Samsun Anadolu Lisesi", 3L}
        );

        Assert.assertArrayEquals(expectedResultSet.toArray(), actualResultSet.toArray());
    }


}
