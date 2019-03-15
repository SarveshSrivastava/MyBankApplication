package com.cg.ba.service;

import com.cg.ba.beans.Account;

public interface RegisterLoginService {
	int register(Account account);
	Account login(long accNo,String password);

}
