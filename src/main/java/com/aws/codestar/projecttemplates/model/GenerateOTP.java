package com.aws.codestar.projecttemplates.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class GenerateOTP {
	
	private String createOTP() {
		Random rand = new Random();
		
		return String.valueOf((rand.nextInt(9990) + 1000));
	}

	public String sendOTP(String phoneNumber) {
		String otp = createOTP();     
		try {
//	            String phoneNumber = "+919284683428";
	            String appKey = "21e1b1c5-944c-493f-99a1-4217ab4051a7";
	            String appSecret = "V17KneSjXEmkgI24FHROuQ==";
	            
	            String message = "Your OTP for Information Updation is :-" + otp;
	            URL url = new URL("https://messagingapi.sinch.com/v1/sms/" + phoneNumber);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setDoOutput(true);
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Content-Type", "application/json");
	            String userCredentials = "application\\" + appKey + ":" + appSecret;
	            byte[] encoded = Base64.encodeBase64(userCredentials.getBytes());
	            String basicAuth = "Basic " + new String(encoded);
	            connection.setRequestProperty("Authorization", basicAuth);
	            String postData = "{\"Message\":\"" + message + "\"}";
	            OutputStream os = connection.getOutputStream();
	            os.write(postData.getBytes());
	            StringBuilder response = new StringBuilder();
	            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String line;
	            while ( (line = br.readLine()) != null)
	                response.append(line);
	            br.close();
	            os.close();
	            System.out.println(response.toString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 		return otp;
	}
}
