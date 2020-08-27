package ru.mantis.appmanager;

import org.openqa.selenium.By;
import ru.mantis.model.Issue;

public class UIHelper extends HelperBase {

    public UIHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public String getCurrentUrl(){
        String url = wd.getCurrentUrl();
        return url.substring(url.lastIndexOf("/")+1);
    }

    public Issue addIssue(Issue issue) {
        click(By.linkText("Report Issue"));
        type(By.id("summary"), issue.getSummary());
        type(By.id("description"), issue.getDescription());
        click(By.xpath("//input[@value='Submit Issue']"));

        String id = wd.findElement(By.cssSelector("td[class='bug-id']")).getText();
        return new Issue().withId(Integer.parseInt(id))
                .withSummary(wd.findElement(By.cssSelector("td[class='bug-summary']")).getText().replace(id + ": ", ""))
                .withDescription(wd.findElement(By.cssSelector("td[class='bug-description']")).getText());
    }
}
