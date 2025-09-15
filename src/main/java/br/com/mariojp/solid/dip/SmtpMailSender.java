package br.com.mariojp.solid.dip;

public class SmtpMailSender implements MailSender {

    private final SmtpClient client;

    public SmtpMailSender(SmtpClient client) {
        this.client = client;
    }

    @Override
    public void send(String to, String subject, String body) {
        // Ajuste aqui se o seu SmtpClient usar outro nome de método:
        client.send(to, subject, body);
        // Se houver connect()/close(), chame-os aqui ao redor do send.
    }
}
