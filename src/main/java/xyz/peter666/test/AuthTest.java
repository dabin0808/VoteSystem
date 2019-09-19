package xyz.peter666.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.peter666.entity.Auth;
import xyz.peter666.service.AuthService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AuthTest {

    @Autowired
    private AuthService authService;

    @Test
    public void testAuth(){
        Auth auth = new Auth();
        auth.setAuthId(5);
        auth.setUserId(2);

        authService.updateAuth(auth);
    }
}
