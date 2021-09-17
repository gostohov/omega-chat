package ru.omega.chat.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;

import java.util.Arrays;
import java.util.Collections;


@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "omega_chat";
    }

    @Bean
    @Override
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean cassandraSession = new CqlSessionFactoryBean();
        cassandraSession.setContactPoints("localhost");
        cassandraSession.setPort(9042);
        cassandraSession.setKeyspaceCreations(
                Collections.singletonList(
                        CreateKeyspaceSpecification.createKeyspace("omega_chat")
                                                   .ifNotExists()
                                                   .withSimpleReplication(1)
                )
        );

        // TODO: убрать вызов deprecated метода
        cassandraSession.setStartupScripts(Arrays.asList(
                "USE omega_chat",
                "CREATE TABLE IF NOT EXISTS messages (" +
                        "username text," +
                        "chatRoomId text," +
                        "date timestamp," +
                        "fromUser text," +
                        "toUser text," +
                        "text text," +
                        "PRIMARY KEY ((username, chatRoomId), date)" +
                ") WITH CLUSTERING ORDER BY (date ASC)"
        ));

        return cassandraSession;
    }
}
