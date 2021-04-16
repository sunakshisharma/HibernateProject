package com.sunakshi.main;

import com.sunakshi.dao.AccountDAO;
import com.sunakshi.dao.AccountDAOImpl;
import com.sunakshi.entity.Account;
import com.sunakshi.exception.InsufficientFundsException;

import java.util.Scanner;

public class UseAccount {
    public static void main(String[] args) {

        Account acc = null;
        AccountDAO dao = new AccountDAOImpl();
        int id, choice;
        boolean result;
        double bal;
        Scanner kb = new Scanner(System.in);
        do {
            System.out.println("Select an option:");
            System.out.println(" 1.Create Account\n 2.Get account details\n 3.Withdraw amount\n 4.Deposit amount\n " +
                    "5.Delete account\n 6.Exit");
            choice = kb.nextInt();
            switch (choice) {
                case 1:

                    acc = new Account();
                    System.out.println("Enter acctid:");
                    acc.setAcctId(kb.nextInt());
                    acc.setAcctName(kb.next());
                    acc.setAcctBal(kb.nextDouble());
                    try {
                        result = dao.createAccount(acc);
                        if (result)
                            System.out.println("Account created!");
                    } catch (Exception ex) {
                        System.out.println("Cannot create the account");
                        ex.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Enter acctid:");
                    id = kb.nextInt();
                    try {
                        acc = dao.getAccountDetails(id);
                        if (acc == null)
                            System.out.println("Account id not found");
                        else
                            System.out.println(acc);
                    } catch (Exception ex) {
                        System.out.println("Cannot read the account");
                        ex.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Enter acctid:");
                    id = kb.nextInt();
                    System.out.println("Enter amount to withdraw:");
                    bal = kb.nextDouble();
                    try {
                        result = dao.updateAccount(id, 'W', bal);
                        if (result == true)
                            System.out.println("Account updated");
                        else
                            System.out.println("Cannot withdraw");
                    } catch (InsufficientFundsException ex) {
                        System.out.println("Sorry! Transaction failed:");
                        System.out.println(ex.getMessage());
                    } catch (Exception ex) {
                        System.out.println("Cannot update the account");
                        ex.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Enter acctid:");
                    id = kb.nextInt();
                    System.out.println("Enter amount to deposit:");
                    bal = kb.nextDouble();
                    try {
                        result = dao.updateAccount(id, 'D', bal);
                        if (result == true)
                            System.out.println("Account updated");
                        else
                            System.out.println("Cannot deposit");
                    } catch (Exception ex) {
                        System.out.println("Cannot update the account");
                        ex.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Enter acctid:");
                    id = kb.nextInt();
                    try {
                        result = dao.closeAccount(id);
                        if (result == true)
                            System.out.println("Account closed");
                        else
                            System.out.println("Cannot close account");
                    } catch (Exception ex) {
                        System.out.println("Cannot delete the account");
                        ex.printStackTrace();
                    }
                    break;
                case 6:
                    System.out.println("Thank you for using the app!");
                    dao.closeResourses();
                    System.exit(0);
                default:
                    System.out.println("Wrong choice !");
            }
        } while (true);
    }
}
