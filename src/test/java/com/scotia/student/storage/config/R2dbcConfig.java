package com.scotia.student.storage.config;

import io.r2dbc.h2.*;
import io.r2dbc.spi.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;
import org.springframework.data.r2dbc.core.*;

@TestConfiguration
public class R2dbcConfig {

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate() {
        H2ConnectionConfiguration configuration = H2ConnectionConfiguration
                .builder()
                .url("jdbc:h2:mem:test_db")
                .username("user-test")
                .password("pass-test")
                .build();
        ConnectionFactory connectionFactory = new H2ConnectionFactory(configuration);
        return new R2dbcEntityTemplate(connectionFactory);
    }
}
