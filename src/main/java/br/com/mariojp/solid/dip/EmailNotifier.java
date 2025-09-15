package br.com.mariojp.solid.dip;

public class EmailNotifier {

    private final MailSender mailSender;

    // Injete por construtor (DIP)
    public EmailNotifier(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Construtor default: escolhe Noop ou SMTP a partir de DRY_RUN/SMTP_AVAILABLE
    public EmailNotifier() {
        this(chooseMailSenderFromConfig());
    }

    private static MailSender chooseMailSenderFromConfig() {
        // Lê tanto System properties (setProperty) quanto variáveis de ambiente
        String dryRunProp = System.getProperty("DRY_RUN");
        String dryRunEnv  = System.getenv("DRY_RUN");
        boolean isDryRun  = "true".equalsIgnoreCase(dryRunProp != null ? dryRunProp : dryRunEnv);

        String smtpProp   = System.getProperty("SMTP_AVAILABLE");
        String smtpEnv    = System.getenv("SMTP_AVAILABLE");
        boolean canUseSmtp = "true".equalsIgnoreCase(smtpProp != null ? smtpProp : smtpEnv);

        if (isDryRun || !canUseSmtp) {
            return new NoopMailSender();
        }
        return new SmtpMailSender(new SmtpClient());
    }

    // API genérica
    public void notify(String to, String subject, String body) {
        mailSender.send(to, subject, body);
    }

    // Conveniência usada no seu Main (e aceitável para o exercício)
    public void welcome(User user) {
        String to = user.getEmail();
        String subject = "Bem-vindo(a), " + user.getName() + "!";
        String body = "Olá " + user.getName() + ", bem-vindo(a)!";
        mailSender.send(to, subject, body);
    }
}
