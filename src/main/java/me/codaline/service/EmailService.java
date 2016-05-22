package me.codaline.service;

import me.codaline.dao.EmailDao;
import me.codaline.dao.UserDaoImpl;
import me.codaline.model.EmailAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
@Service
public class EmailService {
    @Autowired
    EmailDao emailDao;
    @Autowired
    UserDaoImpl userDao;

    private String username = "blogtwitertest@gmail.com";
    private String password = "299792458i";


//    public EmailService(String username, String password) {
//        this.username = username;
//        this.password = password;
//
//
//
//    }

    public void send( String toEmail,String user){
        EmailAccess emailAccess = new EmailAccess();
        Properties props= new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            String code =  "";
            Random random = new Random();
            for (int i = 0; i< 7; i++){

                code= code + String.valueOf(random.nextInt(40097));

            }
            Message message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(username));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //Заголовок письма
            message.setSubject("Блог");
            //Содержимое
            emailAccess.setCode(code);
            emailAccess.setEmail(toEmail);
            emailAccess.setUsername(user);
            emailDao.saveEmail(emailAccess);
            message.setText("Привет -  привет:)  " + emailDao.getUserByCode(code).getUsername()+"  Для активации аккаунта перейди по следуйщей ссылке  http://localhost:8080/getMail/" +code);

            //Отправляем сообщение
            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    public  void confirmEmail(String code){


     emailDao.confirmationEmail(code);

//     emailDao.confirmationEmail( userDao.findByUserName(emailDao.getUserByCode(code).getUsername()),  emailDao.getUserByCode(code));
    }
}