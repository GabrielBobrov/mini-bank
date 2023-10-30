package com.ms.account.entrypoint.rest.assembler;

import com.ms.account.core.model.GetAccountModel;
import com.ms.account.entrypoint.rest.dto.response.GetAccountResponseDTO;
import com.ms.account.entrypoint.rest.mapper.AccountEntrypointMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class AccountAssembler {
    private final AccountEntrypointMapper accountEntrypointMapper;

    public GetAccountResponseDTO toResponse(GetAccountModel getAccountModel) {
        log.info("Class {} method toResponse", this.getClass().getName());

        return accountEntrypointMapper.fromGetAccountModelToGetAccountResponseDTO(getAccountModel);
    }
}
