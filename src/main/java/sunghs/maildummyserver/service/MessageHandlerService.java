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

    /**
     * 추가 처리 로직, smtp의 data stream이 완료 된 이후 실행 됩니다.
     * @param from MAIL FROM
     * @param recipient RCPT TO
     * @param mimeMessage DATA
     */
    public void handle(String from, String recipient, MimeMessage mimeMessage) {
        MailInfo mailInfo = mimeParseHelper.parse(mimeMessage);
        log.info("from : {}, recipient : {}, {}", from, recipient, mailInfo.toString());
    }
}
