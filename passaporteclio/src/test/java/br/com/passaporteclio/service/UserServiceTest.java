package br.com.passaporteclio.service;

import br.com.passaporteclio.domain.entity.User;
import br.com.passaporteclio.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public User mockUserEntity() {
        return User.builder()
                .id(1L)
                .perfil("TESTE")
                .email("testev@email.com")
                .senha("123")
                .build();
    }

    @Test
    public void testloadUserByUsernameComSucesso() {
        when(userRepository.findByEmail(any())).thenReturn(mockUserEntity());

        UserDetails userReturned = userService.loadUserByUsername("testev@email.com");

        Assert.assertEquals("testev@email.com", userReturned.getUsername());
    }

    @Test //(expected = UsernameNotFoundException.class)
    public void testloadUserByUsernameComErro() {
        {
            UsernameNotFoundException exception = assertThrows(
                    UsernameNotFoundException.class, () -> {
                        when(userRepository.findByEmail(any())).thenReturn(null);

                        userService.loadUserByUsername("testev@email.com");
                    }
            );
            assertFalse(exception.getMessage().equals("Email incorreto"));
        }
    }
}
