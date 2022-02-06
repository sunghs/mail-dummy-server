package sunghs.maildummyserver.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties("smtp.server")
public class ServerConfigContext {

    private int port;

    private int messageSizeMb;
}
