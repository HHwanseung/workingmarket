package working.workingmarket.learning;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {

    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Test
    void encodeWithBcryptTest() {
        //given
        String password = "AAABBBCCC123";

        //when
        String encodedPassword = passwordEncoder.encode(password);

        //then
        assertThat(encodedPassword).startsWith("{");
        assertThat(encodedPassword).contains("{bcrypt}");
        assertThat(encodedPassword).isNotEqualTo(password);

    }

    @Test
    void matchTest() {
        // given
        String password = "ASDASD123321";
        String encodedPassword = passwordEncoder.encode(password);

        // when
        boolean isMatch = passwordEncoder.matches(password, encodedPassword);

        // then
        assertThat(isMatch).isTrue();
    }

    @Test
    public void random_password(){
        //given
        String password = "ASDZXC123123";

        //when
        String encodePassword = passwordEncoder.encode(password);
        String encodePassword2 = passwordEncoder.encode(password);

        //then
//        assertThat(encodePassword).isEqualTo(encodePassword2);
        assertThat(encodePassword).isNotEqualTo(encodePassword2);

    }


}

