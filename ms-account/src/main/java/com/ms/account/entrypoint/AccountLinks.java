package com.ms.account.entrypoint;

import com.ms.account.entrypoint.controller.AccountController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AccountLinks {

    public static final TemplateVariables PROJECT_VARIABLES = new TemplateVariables(
            new TemplateVariable("projecao", TemplateVariable.VariableType.REQUEST_PARAM));

    public Link linkToAccounts(String rel) {
        String accountsUrl = linkTo(AccountController.class).toUri().toString();

        return Link.of(UriTemplate.of(accountsUrl, PROJECT_VARIABLES), rel);
    }

    public Link linkToAccount(UUID accountId, String rel) {
        return WebMvcLinkBuilder.linkTo(methodOn(AccountController.class)
                .getAccount(accountId)).withRel(rel);
    }

    public Link linkToAccounts() {
        return linkToAccounts(IanaLinkRelations.SELF.value());
    }

    public Link linkToAccount(UUID accountId) {
        return linkToAccount(accountId, IanaLinkRelations.SELF.value());
    }


}
