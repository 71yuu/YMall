package com.yuu.ymall.web.api.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by Yuu
 * @classname EmailUtil
 * @date 2019/6/28 12:37
 */
@Component
public class EmailUtil {

    private final static Logger log= LoggerFactory.getLogger(EmailUtil.class);

    @Value("${EMAIL_HOST}")
    private String EMAIL_HOST;

    @Value("${EMAIL_USERNAME}")
    private String EMAIL_USERNAME;

    @Value("${EMAIL_PASSWORD}")
    private String EMAIL_PASSWORD;

    @Value("${EMAIL_SENDER}")
    private String EMAIL_SENDER;

    @Value("${SERVER_URL}")
    private String SERVER_URL;

    @Autowired
    private TemplateEngine templateEngine;

    @Async
    public void sendTemplateEmail(String sendTo,String title,String templateName,Object o){

        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

        //设定邮箱服务器配置
        senderImpl.setHost(EMAIL_HOST);
        senderImpl.setUsername(EMAIL_USERNAME);
        senderImpl.setPassword(EMAIL_PASSWORD);
        Properties prop = new Properties();
        //服务器进行认证
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.timeout", "20000");
        //邮箱发送服务器端口,这里设置为465端口
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.socketFactory.port", "465");
        //qq邮箱需开启
        prop.put("mail.smtp.ssl.enable", "true");
        senderImpl.setJavaMailProperties(prop);

        //发送html邮件
        MimeMessage mailMessage = senderImpl.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        //设置邮件内容
        try {
            messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");

            messageHelper.setTo(sendTo);
            messageHelper.setFrom(EMAIL_SENDER);
            messageHelper.setSubject(title);
            Context context = new Context();
            context.setVariable("title",title);
            context.setVariables(ObjectUtil.beanToMap(o));
            //获取模板html代码
            String content = templateEngine.process(templateName, context);
            // true表示HTML格式的邮件
            messageHelper.setText(content, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // 发送邮件
        senderImpl.send(mailMessage);
        log.info("给"+sendTo+"发送邮件成功");
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
