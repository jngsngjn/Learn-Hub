package project.homelearn.password;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.entity.user.User;
import project.homelearn.repository.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Test
    void test() {
        User user = userRepository.findByUsername("manager");
        String password = user.getPassword();
        boolean result = passwordEncoder.matches("1234", password);
        assertThat(result).isTrue();
    }
}