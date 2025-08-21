package com.bank.account.config;

import net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GrpcServerAutoConfiguration.class)
public class GrpcServerConfig {
}
