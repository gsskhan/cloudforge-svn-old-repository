package org.demo.ws;

import com.webservicemart.ws.USZip;
import com.webservicemart.ws.USZipSoap;

public class WSmartClient {
	
	public static void main(String[] args) {
		USZip usZip = new USZip();
		USZipSoap usZipSoap = usZip.getUSZipSoap();
		String result = usZipSoap.validateZip("08863");
		System.out.println("Result = "+ result);
	}

}
