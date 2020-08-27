package ru.mantis.tests;

import org.testng.annotations.Test;
import ru.mantis.model.Issue;

import static org.testng.Assert.assertEquals;

public class CreateIssueTests extends TestBase{

    @Test
    public void testCreateIssue() {
        Issue issue= new Issue().withSummary("Test issue").withDescription("Test issue description");
        app.ui().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));

        Issue created = app.ui().addIssue(issue);

        assertEquals(issue.getSummary(), created.getSummary());
    }
}
