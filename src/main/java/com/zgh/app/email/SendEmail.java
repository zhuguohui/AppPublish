package com.zgh.app.email;


import com.zgh.app.bean.ApkInfo;
import com.zgh.app.bean.EmailItem;

import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;

/**
 * Created by dell on 2018-04-15.
 */
public class SendEmail {


    //主程序测试
    public static void send(EmailItem email, List<ApkInfo> apkInfos) {

        if (email == null || !email.isEmailEnable()) {
            return;
        }
        if (apkInfos == null || apkInfos.size() == 0) {
            return;
        }
        ApkInfo apkInfo = apkInfos.get(0);

        StringBuffer sb = new StringBuffer();
        //邮件内容
        sb.append("您好，您的应用" + apkInfo.apkName + "构建成功，已上传到服务器。<br>二维码在附件中 </br>");
        for (ApkInfo apk : apkInfos) {
            sb.append("<a href=\"" + apk.apkDownLoadUrl + "\">" + apk.customName + "</a></br></br>");
        }

        String content = sb.toString();
        //邮件主题
        String topic = "发布提醒";
        List<String> fileList = new ArrayList<>();
        for (ApkInfo apk : apkInfos) {
            fileList.add(apk.downLoadCodePath);
        }


        //带附件发送
        boolean tag = sendMail(email, topic, content, fileList);
        System.out.println("带附件发送" + tag);

        //不带附件发送
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
    public static boolean sendMail(EmailItem email, String topic, String content, List<String> fileList) {
        boolean bool = false;

        try {

            Address[] addressTO = setAddressTo(email.address);
            MimeMessage message = setMessage(email, addressTO, topic);
            /*添加正文内容*/
            Multipart multipart = new MimeMultipart();
            BodyPart contentPart = new MimeBodyPart();
//            contentPart.setText(content);
            contentPart.setContent(content, "text/html;charset=UTF-8");
            contentPart.setHeader("Content-Type", "text/html; charset=utf-8");
            multipart.addBodyPart(contentPart);

            /*添加附件*/
            int index = 1;
            if (fileList != null && fileList.size() > 0) {
                for (String path : fileList) {
                    MimeBodyPart fileBody = new MimeBodyPart();
                    DataSource source = new FileDataSource(path);
                    fileBody.setDataHandler(new DataHandler(source));

                    fileBody.setFileName("app_download_code_" + index + ".png");
                    multipart.addBodyPart(fileBody);
                    index++;
                }
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
    private static MimeMessage setMessage(EmailItem email, Address[] addressTO, String topic)
            throws MessagingException {

        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", email.smtpUrl);
        props.put("mail.user", email.address);
        props.put("mail.addr", email.address);
        props.put("mail.password", email.password);
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


