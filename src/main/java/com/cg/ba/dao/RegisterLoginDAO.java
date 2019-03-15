package com.cg.ba.dao;

import com.cg.ba.beans.Account;

public interface RegisterLoginDAO {
	int register(Account account);
	Account login(long accNo,String password);

}
