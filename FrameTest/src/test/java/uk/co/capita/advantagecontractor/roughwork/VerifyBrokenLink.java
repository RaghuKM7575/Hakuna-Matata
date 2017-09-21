package uk.co.capita.advantagecontractor.roughwork;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import com.relevantcodes.extentreports.LogStatus;

public class VerifyBrokenLink {

	public static void main(String[] args) {
		
		TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
				    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				        return null;
				    }
				    public void checkClientTrusted(
				        java.security.cert.X509Certificate[] certs, String authType) {
				    }
				    public void checkServerTrusted(
				        java.security.cert.X509Certificate[] certs, String authType) {
				    }
				}};

				   try {
				    SSLContext sc = SSLContext.getInstance("SSL");
				    sc.init(null, trustAllCerts, new java.security.SecureRandom());
				    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				    } catch (Throwable t) {
						t.printStackTrace();
				    }
		
		try{
			String testURL = "https://ec2-54-171-188-122.eu-west-1.compute.amazonaws.com";
			URL url = new URL(testURL);
			HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
			connection.connect();
			System.out.println("Response Message is " + connection.getResponseMessage());
			if(!connection.getResponseMessage().equalsIgnoreCase("OK")){
				System.out.println("Response Message for the URL " + testURL + " is not OK");
							}
			connection.disconnect();
			System.out.println("Response Message for the URL " + testURL + " is  OK");
			} catch(Throwable t){
				t.printStackTrace();
			}
	}

}
