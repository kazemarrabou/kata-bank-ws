package com.kata.bank.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.kata.bank.model.AccountOperation;

public class AccountOperationDaoImpl extends AbstractDao<Integer, AccountOperation> implements AccountOperationDao {

	@Override
	public AccountOperation findById(final int id) {
		return getByKey(id);
	}

	@Override
	public void saveUserAccount(final AccountOperation accountOperation) {
		persist(accountOperation);

	}

	@Override
	public List<AccountOperation> findAccountOperationByIdUser(final int idUser) {
		final Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idUser", idUser));
		return criteria.list();

	}

}
