package smsservice.implemention;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import smsservice.service.SmsService;

public class SmsServiceImpl implements SmsService{

	@Override
	public String SendSms(String number,String content) {
		String mobile = number;
		String message = content;
		
		try {
			URL url = new URL("https://sms.send.lk/api/v3/sms/send");
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Authorization", "Bearer ");
			http.setRequestProperty("Content-Type", "application/json");

			String data = "{\"recipient\": \""+mobile+"\" , \"sender_id\" : \"SendTest\" , \"message\":\""+message+"\"}";

			
			byte[] out = data.getBytes(StandardCharsets.UTF_8);

			OutputStream stream = http.getOutputStream();
			stream.write(out);

			System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			http.disconnect();
			System.out.println("SMS Send Successfully");
		} catch (Exception e) {
			System.out.println("SMS Send Failed");
			e.printStackTrace();
		}
		return null;
	}

}
