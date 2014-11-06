/**
 * JBoss, Home of Professional Open Source Copyright Red Hat, Inc., and individual contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.jboss.aerogear.webpush.standalone;

import org.hamcrest.CoreMatchers;
import org.jboss.aerogear.webpush.WebPushServerConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigReaderTest {

    private static WebPushServerConfig webPushServerConfig;

    static {
        System.setProperty("org.jboss.logging.provider", "slf4j");
    }

    @BeforeClass
    public static void parseConfigFile() {
        webPushServerConfig = ConfigReader.parse(ConfigReaderTest.class.getResourceAsStream("/webpush-test-config.json"));
    }

    @Test
    public void host() {
        assertThat(webPushServerConfig.host(), equalTo("localhost"));
    }

    @Test
    public void port() {
        assertThat(webPushServerConfig.port(), CoreMatchers.is(9999));
        assertThat(webPushServerConfig.password(), CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    @Test
    public void tokenKey() {
        assertThat(webPushServerConfig.password(), CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    @Test
    public void endpointHost() {
        assertThat(webPushServerConfig.endpointHost(), equalTo("external"));
    }

    @Test
    public void endpointPort() {
        assertThat(webPushServerConfig.endpointPort(), CoreMatchers.is(8889));
    }

    @Test
    public void endpointPrefix() {
        assertThat(webPushServerConfig.endpointPrefix(), equalTo("/endpoint"));
    }

    @Test
    public void endpointTls() {
        assertThat(webPushServerConfig.useEndpointTls(), CoreMatchers.is(true));
    }

    @Test
    public void registrationMaxAge() {
        assertThat(webPushServerConfig.registrationMaxAge(), CoreMatchers.is(3000L));
    }

    @Test
    public void channelMaxAge() {
        assertThat(webPushServerConfig.channelMaxAge(), CoreMatchers.is(4000L));
    }

    @Test
    public void sampleConfig() {
        final WebPushServerConfig config = ConfigReader.parse(ConfigReaderTest.class.getResourceAsStream("/webpush-config.json"));
        assertThat(config.host(), equalTo("0.0.0.0"));
        assertThat(config.port(), CoreMatchers.is(7777));
        assertThat(config.password(), CoreMatchers.is(CoreMatchers.notNullValue()));
    }

}
