package com.qq986945193.davidbookstore.common.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.qq986945193.davidbookstore.user.domain.MailInfo;


/******************************************************************************
发送邮件
 */

public class MailSendUtils {

	public static void sendHtmlMail(MailInfo info)throws Exception{
		Message message = getMessage(info);
		message.setContent(info.getContent(), "text/html;charset=utf-8");
		Transport.send(message);
	}
	
	public static void sendTextMail(MailInfo info)throws Exception{
		Message message = getMessage(info);
		message.setText(info.getContent());
		Transport.send(message);
	}
	
	private static Message getMessage(MailInfo info) throws Exception{
		final Properties  p = System.getProperties() ;
		p.setProperty("mail.smtp.host", info.getHost());
		p.setProperty("mail.smtp.auth", "true");
		p.setProperty("mail.smtp.user", info.getFormName());
		p.setProperty("mail.smtp.pass", info.getFormPassword());
		
		Session session = Session.getInstance(p, new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(p.getProperty("mail.smtp.user"),p.getProperty("mail.smtp.pass"));
			}
		});
		session.setDebug(true);
		Message message = new MimeMessage(session);
		message.setSubject(info.getSubject());
		message.setReplyTo(InternetAddress.parse(info.getReplayAddress()));
		message.setFrom(new InternetAddress(p.getProperty("mail.smtp.user"),"网站管理员"));
		message.setRecipient(RecipientType.TO,new InternetAddress(info.getToAddress()));
		
		return message ;
	}
	
	public static void main(String[] args) throws Exception{
		MailInfo info = new MailInfo();
		info.setHost("smtp.163.com");
		info.setFormName("dmcxiaobing@163.com");
		info.setFormPassword("xxxxxxx");
		info.setReplayAddress("dmcxiaobing@163.com");
		info.setToAddress("986945193@qq.com");
		info.setSubject("bbs测试主题邮件");
		info.setContent("这是一封测试邮件");
		sendTextMail(info);
	}
}
