package sunghs.maildummyserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sunghs.maildummyserver.model.MailInfo;

import javax.mail.Header;
import javax.mail.internet.MimeMessage;
import java.util.Enumeration;

@Slf4j
@Component
public class MimeParseHelper {

    public MailInfo parse(MimeMessage mimeMessage) {
        MailInfo mailInfo = new MailInfo();

        try {
            Enumeration<Header> headerEnumeration = mimeMessage.getAllHeaders();

            while (headerEnumeration.hasMoreElements()) {
                Header header = headerEnumeration.nextElement();
                mailInfo.addHeader(header.getName(), header.getValue());
            }

            mailInfo.setContent(mimeMessage.getContent().toString());
            mailInfo.setSubject(mimeMessage.getSubject());
        } catch (Exception e) {
            log.error("mime message parse error", e);
        }
        return mailInfo;
    }
}
