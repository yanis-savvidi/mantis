package ru.mantis.tests;

import org.testng.annotations.Test;
import ru.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword")));
    }

    @Test void testLoginUI(){
        app.ui().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        assertTrue(app.ui().getCurrentUrl().equals("my_view_page.php"));
    }
}
