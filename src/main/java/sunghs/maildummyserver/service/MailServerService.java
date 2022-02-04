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
                .serverThreadName("dummyServer")
                .requireAuth(false)
                .enableTLS(false)
                .softwareName("sunghs mail dummy server with subethamail v1.0")
                .build();

        log.info("require auth : {}", smtpServer.getRequireAuth());
        log.info("server port : {}", smtpServer.getPort());
        log.info("connection timeout : {}", smtpServer.getConnectionTimeout());
        log.info("max connection : {}", smtpServer.getMaxConnections());
        log.info("max recipients : {}", smtpServer.getMaxRecipients());
        log.info("max message size : {}", smtpServer.getMaxMessageSize());
    }

    @PostConstruct
    public void start() {
        smtpServer.start();
    }

    @PreDestroy
    public void stop() {
        if (smtpServer.isRunning()) {
            smtpServer.stop();
        }
    }
}
