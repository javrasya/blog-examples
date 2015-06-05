package net.egemsoft.esef.bigdata.hive.net;

import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;

public class BetterDomainExtractorTest {

    @Test
    public void testUDFWithPublicDomain() throws MalformedURLException {
        BetterDomainExtractor betterDomainExtractor = new BetterDomainExtractor();
        Assert.assertEquals("test.appspot.com", betterDomainExtractor.evaluate(new Text("http://*.test.appspot.com/AdServer/UCookieSetPug?rd=http:/pr-bh.ybp.yahoo.com/sync/pubmatic/#PM_USER_ID")));
    }

    @Test
    public void testUDF() throws MalformedURLException {
        BetterDomainExtractor betterDomainExtractor = new BetterDomainExtractor();
        Assert.assertEquals("testdomain.com", betterDomainExtractor.evaluate(new Text("http://*.test.testdomain.com/AdServer/UCookieSetPug?rd=http:/pr-bh.ybp.yahoo.com/sync/pubmatic/#PM_USER_ID")));
    }

}
