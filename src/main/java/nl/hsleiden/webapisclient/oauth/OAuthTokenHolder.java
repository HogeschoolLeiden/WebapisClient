/*
 * Copyright 2014 Hogeschool Leiden.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
