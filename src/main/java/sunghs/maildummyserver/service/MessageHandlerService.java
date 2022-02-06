package sunghs.maildummyserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sunghs.maildummyserver.model.MailInfo;

import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageHandlerService {

    private final MimeParseHelper mimeParseHelper;

    public void handle(String from, String recipient, MimeMessage mimeMessage) {
        MailInfo mailInfo = mimeParseHelper.parse(mimeMessage);
        log.info("from : {}, recipient : {}, {}", from, recipient, mailInfo.toString());
    }
}
