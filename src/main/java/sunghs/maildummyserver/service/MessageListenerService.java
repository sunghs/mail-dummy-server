package sunghs.maildummyserver.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.subethamail.smtp.helper.SimpleMessageListener;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageListenerService implements SimpleMessageListener {

    private final MessageHandlerService messageHandlerService;

    /**
     * MAIL FROM 및 RCPT TO 의 값은 accpt method 조건이 true가 되어야 통과합니다.
     * @param from MAIL FROM
     * @param recipient RCPT TO
     * @return If the return value is true, proceed to the next step of smtp.
     */
    @Override
    public boolean accept(String from, String recipient) {
        return recipient.contains("@");
    }

    @Override
    @SneakyThrows
    public void deliver(String from, String recipient, InputStream data) {
        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage mimeMessage = new MimeMessage(session, data);
        messageHandlerService.handle(from, recipient, mimeMessage);
    }
}
