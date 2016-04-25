package com.twitter.hbc.core.endpoint;

import com.twitter.hbc.core.endpoint.RealTimeEnterpriseStreamingEndpoint;

public class RealTimeEnterpriseStreamingEndpointV2 extends RealTimeEnterpriseStreamingEndpoint {
    private static final String BASE_PATH = "/stream/%s/accounts/%s/publishers/twitter/%s.json";

    public RealTimeEnterpriseStreamingEndpointV2(String account, String product, String label) {
        super(account, product, label);
    }

    public RealTimeEnterpriseStreamingEndpointV2(String account, String product, String label, int clientId) {
        super(account, product, label, clientId);
    }

    @Override
    public String getURI() {
        String uri = String.format(BASE_PATH, new Object[]{this.product.trim(), this.account.trim(), this.label.trim()});
        return this.queryParameters.isEmpty()?uri:uri + "?" + this.generateParamString(this.queryParameters);
    }
}
