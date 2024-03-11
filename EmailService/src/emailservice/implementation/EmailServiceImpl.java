package emailservice.implementation;

import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import emailservice.service.EmailService;


public class EmailServiceImpl implements EmailService {

	@Override
	public String sendEmail() {
		String recipient = "qwwerrrty11@gmail.com";
        String sender = "qwwerrrty11@gmail.com";
        String subject = "Your Subject";
        String body = "Your email body";
        HttpURLConnection http = null;
        
        try {
            URL url = new URL("https://api.elasticemail.com/v2/email/send");
            http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String apiKey = "";
            String data = "apikey=" + apiKey +
                    "&to=" + recipient +
                    "&from=" + sender +
                    "&subject=" + subject +
                    "&body=" + body;

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);

            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
            System.out.println("Email Sent Successfully");
        } catch (Exception e) {
            System.out.println("Email Send Failed");
            e.printStackTrace();
        }finally {
        	http.disconnect();			
		}
        return null;
	}

}
