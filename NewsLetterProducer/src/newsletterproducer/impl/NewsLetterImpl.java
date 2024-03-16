package newsletterproducer.impl;

import java.sql.Connection;
import java.util.Scanner;

import dbconnection.serviceimpl.DbCon;
import emailservice.implementation.EmailServiceImpl;
import emailservice.service.EmailService;
import newsletterproducer.Modal.NewsLetter;
import newsletterproducer.dao.NewLetterDao;
import newsletterproducer.service.NewLetterService;
import smsservice.implemention.SmsServiceImpl;
import smsservice.service.SmsService;

public class NewsLetterImpl implements NewLetterService{
	Scanner scn = new Scanner(System.in);
	private DbCon databaseConnection = new DbCon();;
	private Connection conn = databaseConnection.getConnection();
	private NewLetterDao newsLetterDao = new NewLetterDao(conn);
	public NewsLetterImpl() {
	}
	@Override
	public void createEmailNewsLetter() {
		try {
			System.out.println("\n\n\n      -------------Send Newsletter to Email-----------\n");
			NewsLetter newsLetter = new NewsLetter();

			System.out.print("Enter Email   - ");
			newsLetter.setEmail(scn.nextLine());

			System.out.print("Enter Letter Content  - ");
			newsLetter.setContent(scn.nextLine());

			int result = 0;
			result = newsLetterDao.createEmailNewsLetter(newsLetter);
			if (result > 0) {
				EmailService emailService = new EmailServiceImpl();
				emailService.sendEmail(newsLetter.getEmail(),newsLetter.getContent());
			} else if (result == 0) {
				System.out.println("Failed to Operation.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Occured");
		}
	}

	@Override
	public void createSmsNewsLetter() {
		try {
			System.out.println("\n\n\n      -------------Send Newsletter to Number-----------\n");
			NewsLetter newsLetter = new NewsLetter();

			System.out.print("Enter Number   - ");
			newsLetter.setNumber(scn.nextLine());

			System.out.print("Enter Letter Content  - ");
			newsLetter.setContent(scn.nextLine());

			int result = 0;
			result = newsLetterDao.createSmsNewsLetter(newsLetter);
			if (result > 0) {
				SmsService smsService = new SmsServiceImpl();
				smsService.SendSms(newsLetter.getNumber(), newsLetter.getContent());
			} else if (result == 0) {
				System.out.println("Failed to Operation.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Occured");
		}
	}
}
