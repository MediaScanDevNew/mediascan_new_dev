/**
 * 
 */
package com.pj.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author pradeep
 *
 */
public class PrimeNumbers {

	/**
	 * 
	 */
	public PrimeNumbers() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 0;
		int num = 0;
		// Empty String
		String primeNumbers = "";
		
		for(int p=0; p<=1000;p=p+100)
		{
			int q=p+100;
			if(q>1000)
			{
				break;
			}
			for (i = p; i <=q; i++) {
				int counter = 0;
				for (num = i; num >= 1; num--) {
					if (i % num == 0) {
						counter = counter + 1;
					}
				}
				if (counter == 2) {
					// Appended the Prime number to the String
					primeNumbers = i+"";
				}
			}
			System.out.println(primeNumbers);
			
		}
		System.out.println(netIsAvailable());
	}
	private static boolean netIsAvailable() {
	    try {
	        final URL url = new URL("http://www.google.com");
	        final URLConnection conn = url.openConnection();
	        conn.connect();
	        return true;
	    } catch (MalformedURLException e) {
	        throw new RuntimeException(e);
	    } catch (IOException e) {
	        return false;
	    }
	}
}
