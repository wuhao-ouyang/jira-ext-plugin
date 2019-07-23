/***************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 **************************************************************************/
package org.jenkinsci.plugins.jiraext.svc.impl;

import hudson.util.Secret;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.jenkinsci.plugins.jiraext.Config;
import org.jenkinsci.plugins.jiraext.svc.JiraClientFactory;

/**
 * Build a JiraClient from global configuration
 *
 * @author dalvizu
 */
public class JiraClientFactoryImpl
    implements JiraClientFactory
{

    @Override
    public JiraClient newJiraClient()
    {
        Config.PluginDescriptor config = Config.getGlobalConfig();
        String jiraUrl = config.getJiraBaseUrl();
        String username = config.getUsername();
        String password = Secret.toString(config.getPassword());

        BasicCredentials creds = new BasicCredentials(username, password);
        JiraClient client = new JiraClient(jiraUrl, creds);
        DefaultHttpClient httpClient = (DefaultHttpClient)client.getRestClient().getHttpClient();
        int timeoutInSeconds = config.getTimeout();
        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), timeoutInSeconds * 1000);
        HttpConnectionParams.setSoTimeout(httpClient.getParams(), timeoutInSeconds * 1000);
        return client;
    }

}
