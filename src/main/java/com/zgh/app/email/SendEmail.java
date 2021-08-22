package com.zgh.app.email;





import java.util.Base64;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 * Created by dell on 2018-04-15.
 */
public class SendEmail {
/*    //发件邮箱的地址
    private final static String MAIL_NAME = "zhuguohui9999@163.com";
    private final static String MAIL_ADDR = "zhuguohui9999@163.com";
    //发件邮箱的密码
    private final static String MAILPWD = "PBSNUZTUWTKAUAJQ";
    //设置发送邮件的协议,下面是163邮箱的SMTP服务器
    private final static String SMTP_HOST = "smtp.163.com";*/

    //发件邮箱的地址
    private final static String MAIL_NAME = "287718603@qq.com";
    private final static String MAIL_ADDR = "287718603@qq.com";
    //发件邮箱的密码
    private final static String MAILPWD = "manfwbcmpmbwbidd";
    //设置发送邮件的协议,下面是163邮箱的SMTP服务器
    private final static String SMTP_HOST = "smtp.qq.com";
    //主程序测试
    public static void main(String[] args) {

        //邮件内容
        String content = "你好，你的应用(读嘉正式版 1.4.4 build 77 )构建成功，已上传fir.下载地址二维码在附件中 <a href=\"http://d.cc53.cn/e6da\">下载链接</a>";
        //邮件主题
        String topic = "发布提醒";
        //接收邮箱地址
        String address = "287718603@qq.com";
        //发送附件路径
        String filename = "D:/qrcode/2021/08/21/app.png";

        //带附件发送
        boolean tag = sendMail(topic, content, address, filename);
        System.out.println("带附件发送"+tag);

        //不带附件发送
//        boolean tag1 = sendMail(topic, content, address);
//        System.out.println("不带附件发送" + tag1);
    }

    /**
     * @author yanawang
     * @Title: sendMail
     * @Description: 发送邮件(可以进行带附件发送, 参数最后为附件地址)
     * @date 2018-2-24 下午 02:28
     * 参数顺序说明：String topic, String content, String address,String file(文件地址选填)
     * 参数至少为3个(标题、内容、接收人地址)
     * 接收人地址可以设置多个，使用','进行分割。如：123@163.com,456@163.com
     */
//    public static boolean sendMail(String topic, String content, String address) {
    public static boolean sendMail(String... strArray) {
        boolean bool = false;

        System.out.println(strArray.length);
        if (strArray.length < 3) {
            return bool;
        }
        try {
            String topic = strArray[0];
            String content = strArray[1];
            String address = strArray[2];
            Address[] addressTO = setAddressTo(address);
            MimeMessage message = setMessage(addressTO, topic);
            /*添加正文内容*/
            Multipart multipart = new MimeMultipart();
            BodyPart contentPart = new MimeBodyPart();
//            contentPart.setText(content);
            contentPart.setContent(content, "text/html;charset=UTF-8");
            contentPart.setHeader("Content-Type", "text/html; charset=utf-8");
            multipart.addBodyPart(contentPart);

            /*添加附件*/
            if (strArray.length > 3) {
                String file = strArray[3];
                File usFile = new File(file);
                MimeBodyPart fileBody = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                fileBody.setDataHandler(new DataHandler(source));

                fileBody.setFileName("app_download_code.png");
                multipart.addBodyPart(fileBody);
            }
            message.setContent(multipart);
            message.setSentDate(new Date());
            message.saveChanges();
            Transport.send(message);
            bool = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * @author yanawang
     * @Title: setMessage
     * @Description: 设置邮箱信息
     * @date 2018-2-5 上午 11:20
     */
    private static MimeMessage setMessage(Address[] addressTO, String topic)
            throws MessagingException {

        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.user", MAIL_NAME);
        props.put("mail.addr", MAIL_ADDR);
        props.put("mail.password", MAILPWD);
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                String userName = props.getProperty("mail.user");
                String password = props
                        .getProperty("mail.password");
                return new PasswordAuthentication(userName,
                        password);
            }
        };
        Session mailSession = Session.getInstance(props,
                authenticator);
        MimeMessage message = new MimeMessage(mailSession);
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.addr"));
        message.setFrom(form);
        message.setRecipients(Message.RecipientType.TO, addressTO);
        //需要抄送给自己，避免163认为是垃圾邮件
        message.addRecipients(Message.RecipientType.CC, MAIL_ADDR);
        message.setSubject(topic);
        message.addHeader("charset", "UTF-8");
        return message;
    }

    /**
     * @author yanawang
     * @Title: setAddressTo
     * @Description: 设置邮箱地址
     * @date 2018-2-5 上午 11:20
     */
    private static Address[] setAddressTo(String address) throws AddressException {
        String[] tmpAddress = address.split(",");
        Address[] addressTO = new InternetAddress[tmpAddress.length];
        for (int i = 0; i < tmpAddress.length; i++) {
            addressTO[i] = new InternetAddress(tmpAddress[i]);
        }
        return addressTO;
    }


}


