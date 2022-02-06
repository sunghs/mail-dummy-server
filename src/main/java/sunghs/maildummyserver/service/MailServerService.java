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
                .maxMessageSize(serverConfigContext.getMessageSizeMb() * 1024 * 1024)
                .simpleMessageListener(messageListenerService)
                .serverThreadName("dummyServer")
                .requireAuth(false)
                .enableTLS(false)
                .softwareName("sunghs mail dummy server with subethamail v1.0")
                .build();

        log.debug("server config context : {}", serverConfigContext.toString());
        log.debug("require auth : {}", smtpServer.getRequireAuth());
        log.debug("server port : {}", smtpServer.getPort());
        log.debug("connection timeout : {}", smtpServer.getConnectionTimeout());
        log.debug("max connection : {}", smtpServer.getMaxConnections());
        log.debug("max recipients : {}", smtpServer.getMaxRecipients());
        log.debug("max message size : {}", smtpServer.getMaxMessageSize());
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
