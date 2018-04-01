package com.lllllw.autosendlog;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AutoSendLog {
	public static void main(String[] args) throws MessagingException, GeneralSecurityException {
		// 创建Properties 类用于记录邮箱的一些属性
		final Properties props = new Properties();
		// 表示SMTP发送邮件，必须进行身份验证
		props.put("mail.smtp.auth", "true");
		// 此处填写SMTP服务器
		props.put("mail.smtp.host", "smtp.qq.com");
		// 端口号
		props.put("mail.smtp.port", "587");
		// 此处填写邮箱的账号
		props.put("mail.user", "945715706@qq.com");
		// 此处的密码就是16位STMP口令
		props.put("mail.password", "ymwikkaortxlbdhi");

		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
		message.setFrom(form);

		// 设置收件人的邮箱
		String[] addresses = new String[] { 
				"zj045505@163.com",
				"jiazhangmin@jiudaotech.com",
				"yangyuxiang@jiudaotech.com", 
				"wangruishou@jiudaotech.com" 
				};
		int len = addresses.length;
		InternetAddress[] to = new InternetAddress[len];

		for (int i = 0; i < addresses.length; i++) {
			to[i] = new InternetAddress(addresses[i]);
		}
		// InternetAddress to1 = new InternetAddress("zj045505@163.com");
		// InternetAddress to2 = new InternetAddress("jz045505@126.com");
		// InternetAddress to3 = new
		// InternetAddress("zj045505@163.com,jz045505@126.com");
		// InternetAddress to4 = new
		// InternetAddress("zj045505@163.com,jz045505@126.com");

		Tool tool = new Tool();
		// 将收件人填入
		message.addRecipients(RecipientType.TO, to);

		// 设置邮件标题
		message.setSubject("陈俊浩" + tool.getdate() + "日报");

		message.setContent(tool.getlog(), "text/html;charset=UTF-8");// 文本格式为html所以换行符为<br>

		// 发送邮件
		Transport.send(message);
	}

}
