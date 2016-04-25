package com.twitter.kinesis.utils;

import com.google.common.base.Preconditions;
import com.twitter.hbc.httpclient.auth.Authentication;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import sun.misc.BASE64Encoder;

public class BasicAuthBase64Encoded implements Authentication {
    private final String username;
    private final String password;

    public BasicAuthBase64Encoded(String username, String password) {
        this.username = (String) Preconditions.checkNotNull(username);
        this.password = (String)Preconditions.checkNotNull(password);
    }

    public void setupConnection(AbstractHttpClient client) {
        client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.username, this.password));
    }

    public void signRequest(HttpUriRequest request, String postParams) {
        BASE64Encoder encoder = new BASE64Encoder();
        String authToken = this.username + ":" + this.password;
        request.addHeader("Authorization", "Basic " + encoder.encode(authToken.getBytes()));
    }
}
