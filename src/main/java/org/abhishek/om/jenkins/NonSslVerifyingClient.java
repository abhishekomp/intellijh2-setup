package org.abhishek.om.jenkins;

import kong.unirest.Unirest;

/**
 * Created by sca820 on 03 sep., 2022
 */
public class NonSslVerifyingClient {
    public NonSslVerifyingClient() {
        Unirest.config().verifySsl(false);
    }
}
