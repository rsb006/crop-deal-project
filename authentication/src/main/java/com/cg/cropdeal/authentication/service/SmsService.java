package com.cg.cropdeal.authentication.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
	private final String msg = "Hi";
	
	@Value("${twilio.phone}")
	private String phone;
	@Value("${twilio.auth}")
	private String auth_token;
	@Value("${twilio.sid}")
	private String account_sid;
	
	public String sendSms(String to_phone) {
		Twilio.init(account_sid, auth_token);
		PhoneNumber toPhone = new PhoneNumber(to_phone);
		PhoneNumber fromPhone = new PhoneNumber(phone);
		
		Message message = Message.creator(toPhone, fromPhone, msg).create();
		
		return message.getSid();
	}
}
