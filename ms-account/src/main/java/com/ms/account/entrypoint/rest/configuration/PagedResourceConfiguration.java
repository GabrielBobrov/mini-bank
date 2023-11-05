package com.ms.account.entrypoint.rest.configuration;

import com.ms.account.core.model.GetAccountModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PagedResourcesAssembler;

@Configuration
public class PagedResourceConfiguration {

    @Bean
    public PagedResourcesAssembler<GetAccountModel> getAccountModelPagedResourcesAssembler() {
        return new PagedResourcesAssembler<>(null, null);
    }

}

