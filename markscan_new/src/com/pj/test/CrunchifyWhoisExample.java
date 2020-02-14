package com.pj.test;

import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.whois.WhoisClient;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class CrunchifyWhoisExample {
 
	public static final String IANA_WHOIS_SERVER = "whois.iana.org";
    public static final int WHOIS_PORT = 43;
 
    public static void main (String[] args) throws Exception {
        String host = "naamkaranserial.com";
 
        // get the address of the authoritative Whois server from IANA
        WhoisClient whoisClient = new WhoisClient();
        whoisClient.connect(IANA_WHOIS_SERVER, WHOIS_PORT);
        String tmpStr = whoisClient.query(host);
        whoisClient.disconnect();
        int idx = tmpStr.indexOf("whois:");
        tmpStr = tmpStr.substring(idx+6).trim();
        String actualServer = tmpStr.substring(0, tmpStr.indexOf("\n"));
 
        // we need to special-case some TLDs
        String tld = host.substring(host.lastIndexOf(".")+1).trim().toLowerCase();
 
        // suppress Japanese characters in output
        if ("jp".equals(tld))
            host += "/e";
 
        // get the actual Whois data
        whoisClient.connect(actualServer, WHOIS_PORT);
        // The prefix "domain " solves the problem with spurious server names
        // (like for google.com, apple.com. yahoo.com, microsoft.com etc.)
        if ("com".equals(tld))
            tmpStr = whoisClient.query("domain "+host);
        else if ("de".equals(tld))
            // The syntax for .de is slightly different.
            tmpStr = whoisClient.query("-T dn "+host);
        else if ("dk".equals(tld))
            // show more information for .dk
            tmpStr = whoisClient.query("--show-handles "+host);
        else
            tmpStr = whoisClient.query(host);
        whoisClient.disconnect();
//        printResults(actualServer, tmpStr);
 
        // if there is a more specific server, query that one too
        idx = tmpStr.indexOf("Whois Server:");
        if (idx != -1) {
            tmpStr = tmpStr.substring(idx+13).trim();
            actualServer = tmpStr.substring(0, tmpStr.indexOf("\n"));
            whoisClient.connect(actualServer, WHOIS_PORT);
            tmpStr = whoisClient.query(host);
            whoisClient.disconnect();
//            printResults(actualServer, tmpStr);
        }
        printResults(actualServer, tmpStr);
    }
 
    private static void printResults (String server, String results) {
        // remove Windows-style line endings
        results = results.replaceAll("\r", "");
        System.out.println("\n\nFrom "+server+":\n");
        System.out.println(results);
    }
}
	