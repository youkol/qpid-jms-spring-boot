/**
 * Copyright (C) 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.youkol.support.qpid.jms.spring.boot.autoconfigure;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Factory to create a {@link JmsConnectionFactory} instance from properties
 * defined in {@link QpidJmsProperties}.
 *
 * @author jackiea
 * @date 2020/12/11
 */
class QpidJmsConnectionFactoryFactory {

    private static final String DEFAULT_REMOTE_URL = "amqp://localhost:5672";

    private final QpidJmsProperties properties;

    private final List<QpidJmsConnectionFactoryCustomizer> factoryCustomizers;

    QpidJmsConnectionFactoryFactory(QpidJmsProperties properties,
            List<QpidJmsConnectionFactoryCustomizer> factoryCustomizers) {
        Assert.notNull(properties, "Properties must not be null");
        this.properties = properties;
        this.factoryCustomizers = (factoryCustomizers != null) ? factoryCustomizers : Collections.emptyList();
    }

    <T extends JmsConnectionFactory> T createConnectionFactory(Class<T> factoryClass) {
        try {
            return this.doCreateConnectionFactory(factoryClass);
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to create JmsConnectionFactory", ex);
        }
    }

    private <T extends JmsConnectionFactory> T doCreateConnectionFactory(Class<T> factoryClass) throws Exception {
        T factory = this.createConnectionFactoryInstance(factoryClass);
        if (StringUtils.hasText(this.properties.getClientId())) {
            factory.setClientID(this.properties.getClientId());
        }

        // Customize the JmsConnectionFactory
        this.customize(factory);

        return factory;
    }

    private <T extends JmsConnectionFactory> T createConnectionFactoryInstance(Class<T> factoryClass)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String remoteUrl = this.determineRemoteUrl();
        String username = this.properties.getUsername();
        String password = this.properties.getPassword();

        if (StringUtils.hasLength(username) && StringUtils.hasLength(password)) {
            return factoryClass.getConstructor(String.class, String.class, String.class)
                .newInstance(username, password, remoteUrl);
        }

        return factoryClass.getConstructor(String.class).newInstance(remoteUrl);
    }

    private void customize(JmsConnectionFactory connectionFactory) {
        for (QpidJmsConnectionFactoryCustomizer factoryCustomizer : this.factoryCustomizers) {
            factoryCustomizer.customize(connectionFactory);
        }
    }

    String determineRemoteUrl() {
        if (StringUtils.hasText(this.properties.getRemoteUrl())) {
            return this.properties.getRemoteUrl();
        }

        return DEFAULT_REMOTE_URL;
    }
}
