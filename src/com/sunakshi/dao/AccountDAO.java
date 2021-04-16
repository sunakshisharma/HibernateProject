package com.sunakshi.dao;

import com.sunakshi.entity.Account;
import com.sunakshi.exception.InsufficientFundsException;

public interface AccountDAO {
    public abstract boolean createAccount(Account obj);
    public Account getAccountDetails(int acctid);
    public abstract boolean updateAccount(int id,char tr,double amt) throws InsufficientFundsException;
    public abstract boolean closeAccount(int acctid);
    public abstract void closeResourses();

}
