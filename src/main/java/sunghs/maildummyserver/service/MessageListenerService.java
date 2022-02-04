package sunghs.maildummyserver.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.subethamail.smtp.helper.SimpleMessageListener;
import sunghs.maildummyserver.model.MailInfo;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageListenerService implements SimpleMessageListener {

    private final MimeParseHelper mimeParseHelper;

    @Override
    public boolean accept(String from, String recipient) {
        return recipient.contains("@");
    }

    @Override
    @SneakyThrows
    public void deliver(String from, String recipient, InputStream data) {
        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage mimeMessage = new MimeMessage(session, data);
        MailInfo mailInfo = mimeParseHelper.parse(mimeMessage);
        log.info("from : {}, recipient : {}, {}", from, recipient, mailInfo.toString());
    }
}
