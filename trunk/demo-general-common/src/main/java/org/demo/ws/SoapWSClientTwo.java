package org.demo.ws;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Iterator;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;

public class SoapWSClientTwo {
	
    /*
    Constructed SOAP Request Message:
    <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
        <SOAP-ENV:Header/>
        <SOAP-ENV:Body>
            <example:VerifyEmail xmlns:example="http://ws.cdyne.com/">
                <example:email>mutantninja@gmail.com</example:email>
                <example:LicenseKey>123</example:LicenseKey>
            </example:VerifyEmail>
        </SOAP-ENV:Body>
    </SOAP-ENV:Envelope>
     */
	
	public static Logger log = Logger.getLogger(SoapWSClientTwo.class.getName());

	public static void main(String[] args) {
		// set the MessageFactory System property
		// System.setProperty("javax.xml.soap.MessageFactory", "com.sssw.jbroker.saaj.soap.MessageFactoryImpl");
		// set the ConnectionFactory System property
		// System.setProperty("javax.xml.soap.SOAPConnectionFactory", "com.sssw.jbroker.saaj.soap.SOAPConnectionFactoryImpl");
		    
		log.info("program started ...");
		try {
			// First create the connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			URL destination = new URL("http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx");
			
			// Next, create the actual message
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage message = messageFactory.createMessage();
			
			SOAPPart soapPart = message.getSOAPPart();
			SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
			
			// To set HTTP and SOAP headers (Optional).
            MimeHeaders mimeHeaders = message.getMimeHeaders();
            mimeHeaders.addHeader("Content-Type", "text/xml; charset=UTF-8");
            
            // To remove the Soap Header (Optional)
            SOAPHeader soapHeader = soapEnvelope.getHeader();
            soapHeader.detachNode();
            
            // Create and populate the body
            SOAPBody soapBody = soapEnvelope.getBody();
            
            	// Create the main element and namespace
            	SOAPElement bodyElement = soapBody.addChildElement("VerifyEmail", "example", "http://ws.cdyne.com/");
            	SOAPElement childElementOne = bodyElement.addChildElement("email", "example");
            				childElementOne.addTextNode("g.s.s.khan@gmail.com");
            	SOAPElement childElementTwo = bodyElement.addChildElement("LicenseKey", "example");
            				childElementTwo.addTextNode("123");
            	
            // Save the message
            message.saveChanges();	
            
            // Print the request message 
            log.info("Request SOAP Message:");
            //message.writeTo(System.out);
            log.info("\n" +getSOAPMessageAsString(message));
            
            // Send the message and get the reply
            SOAPMessage reply = soapConnection.call(message, destination);
            
            // print SOAP reply
            log.info("Response SOAP Message:");
            log.info("\n"+ getSOAPMessageAsString(reply));
			
						
		} catch (Exception e) {
			log.error("error in calling WS.", e);
		}
	}
	
	public static String getSOAPMessageAsString(SOAPMessage soapMessage) {
	      try {

	         TransformerFactory tff = TransformerFactory.newInstance();
	         Transformer tf = tff.newTransformer();
	         String strMessage = "";

	         // Set formatting	        
	         tf.setOutputProperty(OutputKeys.INDENT, "yes");
	         tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	         
	         Iterator<MimeHeader> itr = soapMessage.getMimeHeaders().getAllHeaders();
	         while (itr.hasNext()) {
				MimeHeader mimeHeader = (MimeHeader) itr.next();
				strMessage = strMessage + mimeHeader.getName()+":"+mimeHeader.getValue()+"\n";				
	         } 

	         Source sc = soapMessage.getSOAPPart().getContent();

	         ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
	         StreamResult result = new StreamResult(streamOut);
	         tf.transform(sc, result);

	         strMessage = strMessage + streamOut.toString();
	         return strMessage;
	      } catch (Exception e) {
	         log.error("Exception in getSOAPMessageAsString ", e);
	         return null;
	      }

	   }

}
