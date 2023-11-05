package com.ms.account.infrastructure.specs;

import com.ms.account.infrastructure.entity.AccountEntity;
import com.ms.account.infrastructure.filter.AccountFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Objects;

public class AccountSpecs {
	private AccountSpecs() {
	}

	public static Specification<AccountEntity> usingFilter(AccountFilter accountFilter) {
		return (root, query, builder) -> {
			var predicates = new ArrayList<Predicate>();

			if (Objects.nonNull(accountFilter.getId())) {
				predicates.add(builder.equal(root.get("id"), accountFilter.getId()));
			}

			if (Objects.nonNull(accountFilter.getType())) {
				predicates.add(builder.equal(root.get("type"), accountFilter.getType()));
			}

			if (Objects.nonNull(accountFilter.getFirstName())) {
				predicates.add(builder.equal(root.get("firstName"), accountFilter.getFirstName()));
			}

			if (Objects.nonNull(accountFilter.getDocument())) {
				predicates.add(builder.equal(root.get("document"), accountFilter.getDocument()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}