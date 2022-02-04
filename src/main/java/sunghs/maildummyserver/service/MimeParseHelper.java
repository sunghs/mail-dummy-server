package sunghs.maildummyserver.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.util.MimeMessageParser;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Slf4j
@Component
public class MimeParseHelper {

    public MimeMessageParser parse(MimeMessage mimeMessage) {
        MimeMessageParser mimeMessageParser = new MimeMessageParser(mimeMessage);
        try {
            return mimeMessageParser.parse();
        } catch (Exception e) {
            log.error("mime message parse error", e);
            return mimeMessageParser;
        }
    }
}
