package com.ms.account.entrypoint.rest.assembler;

import com.ms.account.core.model.GetAccountModel;
import com.ms.account.entrypoint.rest.dto.response.GetAccountResponseDTO;
import com.ms.account.entrypoint.rest.mapper.IAccountEntrypointMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class AccountAssembler {
    private final IAccountEntrypointMapper accountEntrypointMapper;

    public GetAccountResponseDTO toResponse(GetAccountModel getAccountModel) {
        log.info("Class {} method toResponse", this.getClass().getName());

        return accountEntrypointMapper.fromGetAccountModelToGetAccountResponseDTO(getAccountModel);
    }
}
