# Qpid JMS Spring Boot

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.youkol.support.qpid/qpid-jms-spring-boot-starter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.youkol.support.qpid/qpid-jms-spring-boot-starter)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/com.youkol.support.qpid/qpid-jms-spring-boot-starter?server=https%3A%2F%2Foss.sonatype.org)](https://oss.sonatype.org/content/repositories/snapshots/com/youkol/support/qpid/qpid-jms-spring-boot-starter/)
[![License](https://img.shields.io/badge/license-apache-brightgreen)](http://www.apache.org/licenses/LICENSE-2.0.html)

Apache Qpid Jms (AMQP 1.0) for spring boot autoconfigure.

This project provides an easy way to get spring-jms application using 
Apache Qpid JMS that uses the AMQP 1.0.

The project provides a Spring Boot auto-configuration module which allows your application to quickly grab a JMSTemplate that is properly configured to use the Apache Qpid JMS client (AMQP 1.0) as the underlying transport.

### Usage in Maven
To use the Apache Qpid JMS starter (support AMQP 1.0) in your projects,
you can include the maven dependency in your project pom file:
```xml
<dependency>
    <groupId>com.youkol.support.qpid</groupId>
    <artifactId>qpid-jms-spring-boot-starter</artifactId>
    <version>${qpid-jms-starter.version}</version>
</dependency>
```
### Spring-boot configuration
The following options can be used in an 'application.properties' or 'application.yml' file to configure you Spring Boot project.

#### JMS Connection Configuration
* __youkol.qpid-jms.remote-url__
The basic format of the clients Connection URI is as follows:
 > amqp[s]://hostname:port[?option=value[&option2=value...]]   

or for WebSocket connections:
 > amqpws[s]://hostname:port[/path][?option=value[&option2=value...]]   

Where the amqps and amqpwss scheme is specified to use SSL/TLS, the hostname segment from the URI can be used by the JVM for the TLS SNI (Server Name Indication) extension in order to communicate the desired server hostname during a TLS handshake. The SNI extension will be automatically included if a Fully Qualified name (e.g myhost.mydomain) is specified, but not when an unqualified name (e.g myhost) or bare IP address are used.

The client can be configured with a number of different settings using the URI while defining the ConnectionFactory and at present
 only some common options can be configured in the spring boot application properties.   
For a complete overview of the various configuration options available on the connection URI refer to the [Qpid JMS client documentation](http://qpid.apache.org/components/jms/index.html)
* __youkol.qpid-jms.username__ User name value used to connection the remote-url.
* __youkol.qpid-jms.password__ The password value used to connection the remote-url.
* __youkol.qpid-jms.client-id__ The ClientID value that is applied to the connection.
#### Connection Pooling Options (org.messaginghub.pooled-jms)
| Key | Default Value | Description |
| ---- | ---- | ---- |
| __youkol.qpid-jms.pool.block-if-full__ | true | Whether to block when a connection is requested and the pool is full. Set it to false to throw a "JMSException" instead. |
| __youkol.qpid-jms.pool.block-if-full-timeout__ | -1ms | Blocking period before throwing an exception if the pool is still full. |
| __youkol.qpid-jms.pool.enabled__ |false | Whether a JmsPoolConnectionFactory should be created, instead of a regular ConnectionFactory. |
| __youkol.qpid-jms.pool.idle-timeout__ | 30s | Connection idle timeout. |
| __youkol.qpid-jms.pool.max-connections__ | 1 | Maximum number of pooled connections. |
| __youkol.qpid-jms.pool.max-sessions-per-connection__ | 500 | Maximum number of pooled sessions per connection in the pool. |
| __youkol.qpid-jms.pool.time-between-expiration-check__ | -1ms | Time to sleep between runs of the idle connection eviction thread. When negative, no idle connection eviction thread runs. |
| __youkol.qpid-jms.pool.use-anonymous-producers__ | true | Whether to use only one anonymous "MessageProducer" instance. Set it to false to create one "MessageProducer" every time one is required. |
