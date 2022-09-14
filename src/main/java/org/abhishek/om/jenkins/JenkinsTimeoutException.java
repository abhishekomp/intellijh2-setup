package org.abhishek.om.jenkins;

/**
 * Created by sca820 on 03 sep., 2022
 */
public class JenkinsTimeoutException extends RuntimeException {
    public JenkinsTimeoutException(String message) {
        super(message);
    }
}
