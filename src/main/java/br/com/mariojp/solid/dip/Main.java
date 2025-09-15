package br.com.mariojp.solid.dip;

public class Main {
    public static void main(String[] args) {
        // DRY_RUN=true => usa NoopMailSender e não tenta SMTP
        System.setProperty("DRY_RUN", "true");
        System.setProperty("SMTP_AVAILABLE", "false");

        EmailNotifier notifier = new EmailNotifier();
        notifier.welcome(new User("Ana", "ana@example.com"));

        System.out.println("OK (DRY_RUN) — não enviou SMTP de verdade.");
    }
}
