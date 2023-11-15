package com.minibank.transfers.infrastructure.integration.configuration.webclient;


import com.minibank.transfers.core.exception.NotificationException;
import com.minibank.transfers.infrastructure.integration.configuration.webclient.model.WebClientErrorResponse;
import com.minibank.transfers.infrastructure.integration.ms.account.client.MsAccountHttpClient;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;


@Slf4j
@Configuration
@AllArgsConstructor
public class WebClientConfiguration {

    private final MsAccountProperties msAccountProperties;
    
    @Bean
    MsAccountHttpClient transferFacade(){
        return this.createConnection(msAccountProperties.getHost(), MsAccountHttpClient.class);
    }



    private <S> S createConnection(String url, Class<S> hostInterface) throws NotificationException {

        log.info("url: {}, class {}", url, hostInterface.getSimpleName());

        HttpClient httpClient = HttpClient.create()
            .responseTimeout(Duration.ofSeconds(10))
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
            .doOnConnected(conn -> conn
                .addHandlerLast(new ReadTimeoutHandler(10))
                .addHandlerLast(new WriteTimeoutHandler(10))
            );

        WebClient client = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .baseUrl(url)
            .defaultStatusHandler(HttpStatusCode::isError, resp -> resp.bodyToMono(WebClientErrorResponse.class)
                .flatMap(body -> {
                    var message = body.getMessage();
                    var value   = resp.statusCode().value();
                    log.error("message: {}, value {}", message, value);
                    return Mono.error(new NotificationException(message, value));
                }))
           .build();

        HttpServiceProxyFactory proxyFactory;

        proxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client))
            .blockTimeout(Duration.ofSeconds(20))
        .build();

        return proxyFactory.createClient(hostInterface);
    }



}