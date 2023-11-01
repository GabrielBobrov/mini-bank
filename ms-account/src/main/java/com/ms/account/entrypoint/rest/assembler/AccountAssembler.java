package com.ms.account.entrypoint.rest.assembler;

import com.ms.account.core.model.GetAccountModel;
import com.ms.account.entrypoint.rest.AccountLinks;
import com.ms.account.entrypoint.rest.controller.AccountController;
import com.ms.account.entrypoint.rest.dto.response.GetAccountResponseDTO;
import com.ms.account.entrypoint.rest.mapper.IAccountEntrypointMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountAssembler extends RepresentationModelAssemblerSupport<GetAccountModel, GetAccountResponseDTO> {
    private final IAccountEntrypointMapper accountEntrypointMapper;
    private final AccountLinks accountLinks;


    public AccountAssembler(IAccountEntrypointMapper accountEntrypointMapper,
                            AccountLinks accountLinks) {
        super(AccountController.class, GetAccountResponseDTO.class);
        this.accountEntrypointMapper = accountEntrypointMapper;
        this.accountLinks = accountLinks;
    }

    @Override
    @NonNull
    public GetAccountResponseDTO toModel(@NonNull GetAccountModel getAccountModel) {
        log.info("Class {} method toModel", this.getClass().getName());

        GetAccountResponseDTO getAccountResponseDTO = accountEntrypointMapper.fromGetAccountModelToGetAccountResponseDTO(getAccountModel);
        getAccountResponseDTO.add(accountLinks.linkToAccount(getAccountResponseDTO.getId()));
        return getAccountResponseDTO;
    }
}
