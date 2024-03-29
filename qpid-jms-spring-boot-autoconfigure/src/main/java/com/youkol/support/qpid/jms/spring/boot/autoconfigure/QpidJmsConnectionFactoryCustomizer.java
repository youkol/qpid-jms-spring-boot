/*
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

import org.apache.qpid.jms.JmsConnectionFactory;

/**
 * Callback interface that can be implemented by beans wishing to customize the
 * {@link JmsConnectionFactory} whilst retaining default wuto-configuration.
 *
 * @author jackiea
 * @date 2020/12/04
 */
@FunctionalInterface
public interface QpidJmsConnectionFactoryCustomizer {

    /**
     * Customize the {@link JmsConnectionFactory}.
     * @param factory the factory to customize
     */
    void customize(JmsConnectionFactory factory);
}
