package sunghs.maildummyserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class MailInfo {

    private Map<String, String> header;

    private String subject;

    private String content;

    public MailInfo() {
        header = new HashMap<>();
        subject = null;
        content = null;
    }

    public void addHeader(String k, String v) {
        header.put(k, v);
    }
}
