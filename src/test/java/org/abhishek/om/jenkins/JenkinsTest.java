package org.abhishek.om.jenkins;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sca820 on 03 sep., 2022
 */
class JenkinsTest {

    Jenkins j = new Jenkins("abhisk", "abhisk");

    @Test
    void handshakeWithJenkins() {
        String handshakeWithJenkins = j.handshakeWithJenkins();
        System.out.println("handshakeWithJenkins = " + handshakeWithJenkins);
    }
}