package sunghs.maildummyserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.subethamail.smtp.server.SMTPServer;
import sunghs.maildummyserver.config.ServerConfigContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServerService {

    private final ServerConfigContext serverConfigContext;

    private final MessageListenerService messageListenerService;

    private SMTPServer smtpServer;

    @PostConstruct
    private void init() {
        smtpServer = SMTPServer
                .port(serverConfigContext.getPort())
                .simpleMessageListener(messageListenerService)
                .requireAuth(false)
                .build();
    }

    @PostConstruct
    public void start() {
        if (serverConfigContext.isEnabled()) {
            smtpServer.start();
        } else {
            log.error("mail server start failed");
        }
    }

    @PreDestroy
    public void stop() {
        if (smtpServer.isRunning()) {
            smtpServer.stop();
        }
    }
}
