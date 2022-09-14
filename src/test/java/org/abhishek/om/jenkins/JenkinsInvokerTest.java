package org.abhishek.om.jenkins;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sca820 on 14 sep., 2022
 */
class JenkinsInvokerTest {
    JenkinsInvoker jenkinsInvoker = new JenkinsInvoker();

    @Test
        //this worked fine to invoke a maven project on jenkins by passing the junit5 tag names for the test method to execute
    void invokeJenkinsJob() {
        Map<String, Object> parameters = new Parameters()
                .add("cmdlineparams", "-Dgroups=Rel1,Rel4")
                .build();
        String jobName = "/job/GitHubMavenProject";
        String jenkinsHost = "localhost:8080";
        jenkinsInvoker.invokeJenkinsJob("abhisk", "abhisk", jenkinsHost, jobName, parameters);
    }

    @Test
        //this worked fine to invoke a maven project on jenkins by passing the junit5 test class name to invoke
    void invokeJenkinsJobOptionalScenariosDemoTest() {
        Map<String, Object> parameters = new Parameters()
                .add("cmdlineparams", "-Dtest=OptionalScenariosDemoTest")
                .build();
        String jobName = "/job/GitHubMavenProject";
        String jenkinsHost = "localhost:8080";
        jenkinsInvoker.invokeJenkinsJob("abhisk", "abhisk", jenkinsHost, jobName, parameters);
    }

    @Test
        //This test method invokes the Maven Jenkins job using parameters. Worked fine
    void invokeJenkinsJobOptionalScenariosDemoTestAsParameters() {
        Map<String, Object> parameters = new Parameters()
                .add("cmdlineparams", "-Dtest", "OptionalScenariosDemoTest")
                .build();
//        Map<String, Object> parameters = new Parameters()
//                .add("cmdlineparams", "-Dtest=OptionalScenariosDemoTest")
//                .build();
        String jobName = "/job/GitHubMavenProject";
        String jenkinsHost = "localhost:8080";
        jenkinsInvoker.invokeJenkinsJob("abhisk", "abhisk", jenkinsHost, jobName, parameters);
    }

    @Test
        //This test method invokes the Freestyle Jenkins job by sending a String parameter
    void invokeFreeStyleJenkinsJobBySendingASingleStringParameter() {
        //The parameterized Jenkins job has a String parameter defined. Name of the parameter in Jenkins job configuration "inputValue"
        Map<String, Object> parameters = new Parameters()
                .add("inputValue", "Hello Abhishek")
                .build();
        String jobName = "/job/TestFreestyle";
        String jenkinsHost = "localhost:8080";
        jenkinsInvoker.invokeJenkinsJob("abhisk", "abhisk", jenkinsHost, jobName, parameters);
    }
}