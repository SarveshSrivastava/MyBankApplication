package com.cg.ba.service;

import com.cg.ba.beans.Account;

public interface RegisterLoginService {
	int register();
	double login(long accNo,String password);

}
