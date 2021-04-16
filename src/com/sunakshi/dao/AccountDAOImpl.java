package com.sunakshi.dao;

import com.sunakshi.entity.Account;
import com.sunakshi.exception.InsufficientFundsException;
import com.sunakshi.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDAOImpl implements AccountDAO{
    @Override
    public boolean createAccount(Account obj) {
        Session sess= HibernateUtils.getSessionFactory().openSession();
        Transaction tx=sess.beginTransaction();
        sess.save(obj);
        tx.commit();
        sess.close();
        return true;
    }

    @Override
    public Account getAccountDetails(int acctid) {
        Session sess= HibernateUtils.getSessionFactory().openSession();
        Account acc=sess.get(Account.class,acctid);
        sess.close();
        return acc;
    }

    @Override
    public boolean updateAccount(int id, char tr, double amt) throws InsufficientFundsException {
        Session sess= HibernateUtils.getSessionFactory().openSession();
        Transaction tx=sess.beginTransaction();
        Account acc=getAccountDetails(id);
        if (acc == null) {
            tx.commit();
            return false;
        }
        if (tr == 'W') {
            if (acc.getAcctBal() < amt) {
                tx.commit();
                throw new InsufficientFundsException("Balance too low");
            }
            acc.setAcctBal(acc.getAcctBal() - amt);
        } else
            acc.setAcctBal(acc.getAcctBal() + amt);
        sess.update(acc);
        tx.commit();
        sess.close();
        return true;
    }

    @Override
    public boolean closeAccount(int acctid) {
        Session sess= HibernateUtils.getSessionFactory().openSession();
        Transaction tx=sess.beginTransaction();
        Account acc=getAccountDetails(acctid);
        if (acc == null) {
            tx.commit();
            return false;
        }
        sess.delete(acc);
        tx.commit();
        sess.close();
        return true;

    }

    @Override
    public void closeResourses() {
        HibernateUtils.closeSessionFactory();
    }
}
