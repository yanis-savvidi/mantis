package ru.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import ru.mantis.model.Issue;
import ru.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class ApiHelper {
    private ApplicationManager app;

    public ApiHelper(ApplicationManager app) {
        this.app = app;
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        MantisConnectPortType mc = new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("api.url")));
        return mc;
    }

    public Issue getIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        String login = app.getProperty("api.login");
        String password = app.getProperty("api.password");
        MantisConnectPortType mc = getMantisConnect();
        if(!mc.mc_issue_exists(login, password, BigInteger.valueOf(issueId))) return null;
        IssueData issueData = mc.mc_issue_get(login, password, BigInteger.valueOf(issueId));
        return new Issue().withId(issueData.getId().intValue()).withSummary(issueData.getSummary())
                .withDescription(issueData.getDescription()).withStatus(issueData.getStatus().getId().intValue())
                .withProject(new Project().withId(issueData.getProject().getId().intValue())
                        .withName(issueData.getProject().getName()));

    }
}
