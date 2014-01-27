/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.webapisclient.oauth;

import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;

/**
 *
 * @author hl
 */
public class OAuthTokenHolder {
    
    private String accessToken;
    private static OAuth2CodeGrantFlow flow;
    private static ClientIdentifier clientIdentifier;

    
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public OAuth2CodeGrantFlow getFlow() {
        return flow;
    }

    public void setFlow(OAuth2CodeGrantFlow flow) {
        OAuthTokenHolder.flow = flow;
    }

    public ClientIdentifier getClientIdentifier() {
        return clientIdentifier;
    }

    public void setClientIdentifier(ClientIdentifier clientIdentifier) {
        OAuthTokenHolder.clientIdentifier = clientIdentifier;
    }
}
