package br.com.mariojp.solid.dip;

public class NoopMailSender implements MailSender {
    @Override
    public void send(String to, String subject, String body) {
        // DRY_RUN: intencionalmente não envia nada
    }
}
