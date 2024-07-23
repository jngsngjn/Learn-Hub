package project.homelearn.service.manager;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailCodeService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final int ALPHABET_COUNT = 3;
    private static final int DIGIT_COUNT = 3;

    public String sendVerificationCode(String to) throws MessagingException {
        String verificationCode = generateVerificationCode();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Context context = new Context();
        context.setVariable("verificationCode", verificationCode);
        String html = templateEngine.process("email/verificationEmail", context);

        helper.setTo(to);
        helper.setSubject("[Home Learn] 회원가입 인증 코드");
        helper.setText(html, true);

        javaMailSender.send(message);
        log.info("toEmail = {}", to);
        log.info("verificationCode = {}", verificationCode);

        return verificationCode;
    }

    private String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(ALPHABET_COUNT + DIGIT_COUNT);

        for (int i = 0; i < ALPHABET_COUNT; i++) {
            int index = random.nextInt(UPPERCASE_CHARACTERS.length());
            code.append(UPPERCASE_CHARACTERS.charAt(index));
        }

        for (int i = 0; i < DIGIT_COUNT; i++) {
            int index = random.nextInt(DIGITS.length());
            code.append(DIGITS.charAt(index));
        }

        List<Character> codeChars = new ArrayList<>();
        for (char c : code.toString().toCharArray()) {
            codeChars.add(c);
        }
        Collections.shuffle(codeChars);

        StringBuilder shuffledCode = new StringBuilder();
        for (char c : codeChars) {
            shuffledCode.append(c);
        }

        return shuffledCode.toString();
    }
}