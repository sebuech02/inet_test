package com.example.inet_test;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class javamailapi extends AsyncTask<Void,Void,Void>  {


        //Add those line in dependencies
        //implementation files('libs/activation.jar')
        //implementation files('libs/additionnal.jar')
        //implementation files('libs/mail.jar')

        //Need INTERNET permission

        //Variables
        private Context mContext;
        private Session mSession;

        private String mEmail;
        private String mSubject;
        private String mMessage;

        private ProgressDialog mProgressDialog;

        //Constructor
        public  javamailapi(Context mContext, String mEmail, String mSubject, String mMessage) {
            this.mContext = mContext;
            this.mEmail = mEmail;
            this.mSubject = mSubject;
            this.mMessage = mMessage;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Show progress dialog while sending email
            mProgressDialog = ProgressDialog.show(this.mContext,"Sending message", "Please wait...",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Dismiss progress dialog when message successfully send
            mProgressDialog.dismiss();

            //Show success toast
            Toast.makeText(this.mContext,"Spiel gesendet, wartet auf Freigabe",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //Creating properties
            Properties props = new Properties();

            //Configuring properties for gmail
            //If you are not using gmail you may need to change the values
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            //props.put("mail.smtp.starttls.enable", "true");

            //Creating a new session
            mSession = Session.getDefaultInstance(props, new GMailAuthenticator(util.EMAIL, util.PASSWORD));

            try {
                //Creating MimeMessage object
                MimeMessage mm = new MimeMessage(this.mSession);
                //Setting sender address
                mm.setFrom(new InternetAddress(util.EMAIL));
                //Adding receiver
                mm.addRecipient(Message.RecipientType.TO, new InternetAddress(this.mEmail));
                //Adding subject
                mm.setSubject(this.mSubject);
                //Adding message
                mm.setText(this.mMessage);
                //Sending email
                Transport.send(mm);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
}
