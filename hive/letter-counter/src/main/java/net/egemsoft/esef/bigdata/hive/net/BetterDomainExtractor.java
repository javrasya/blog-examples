package net.egemsoft.esef.bigdata.hive.net;

import com.google.common.net.InetAddresses;
import com.google.common.net.InternetDomainName;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.net.MalformedURLException;
import java.net.URL;


public final class BetterDomainExtractor extends UDF {


    public String evaluate(final Text input) throws MalformedURLException {
        if (input == null) return null;


        URL url = new URL(input.toString());

        String host = url.getHost();


        if (host.startsWith("*.")) {
            host = host.replace("*.", "");
        }

        if (InetAddresses.isInetAddress(host)) {
            return host;
        } else {
            try {
                InternetDomainName fullDomainName = InternetDomainName.from(host);
                if (fullDomainName.isUnderPublicSuffix()) {
                    return fullDomainName.topPrivateDomain().name();
                } else {
                    return fullDomainName.name();
                }
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

}