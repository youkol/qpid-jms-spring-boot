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

import jakarta.jms.ConnectionFactory;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Auto Configuration for Apache Qpid JMS
 * {@link EnableAutoConfiguration Auto-configuration} to integrate with Apache Qpid JMS.
 * Validates that the classpath contain the necessary classes before starting qpid jms.
 *
 * @author jackiea
 * @date 2020/12/11
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(JmsAutoConfiguration.class)
@AutoConfigureAfter({ JndiConnectionFactoryAutoConfiguration.class })
@ConditionalOnClass({ ConnectionFactory.class, JmsConnectionFactory.class })
@ConditionalOnMissingBean(ConnectionFactory.class)
@EnableConfigurationProperties({ QpidJmsProperties.class, JmsProperties.class })
@Import({ QpidJmsConnectionFactoryConfiguration.class })
public class QpidJmsAutoConfiguration {

}
