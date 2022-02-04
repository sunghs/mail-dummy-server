package sunghs.maildummyserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MailInfo {

    private String subject;

    private String content;
}
