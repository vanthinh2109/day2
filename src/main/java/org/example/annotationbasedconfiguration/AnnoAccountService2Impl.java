package org.example.annotationbasedconfiguration;

import org.example.pojo.Account;
import org.example.pojo.AccountReponsitory;
import org.example.pojo.AccountService;

public  class AnnoAccountService2Impl implements AccountService {
//     AccountReponsitory is a depency of AccountServiceImpl
            private AccountReponsitory accountReponsitory;
//            Depency injection via Setter method.
            public void setAccountReponsitory(AccountReponsitory accountReponsitory) {
                this.accountReponsitory = accountReponsitory;
            }
            @Override
            public void transferMoney(long fromAccountId, long toAccountId, double amount){
                Account sourceAccount = accountReponsitory.find(fromAccountId);
                Account targetAccount = accountReponsitory.find(toAccountId);
                sourceAccount.setBalance(sourceAccount.getBalance() - amount);
                targetAccount.setBalance(targetAccount.getBalance() + amount);
                accountReponsitory.update(sourceAccount);
                accountReponsitory.update(targetAccount);
            }

    @Override
    public void deponsitMoney(long accountId, double amount) throws Exception {

    }

    @Override
            public void depositMoney(long accountId, double amount) throws Exception{
                Account account = accountReponsitory.find(accountId);
                account.setBalance(account.getBalance() + amount);
                accountReponsitory.update(account);
            }
            @Override
            public Account getAccount(long accountId){
                return accountReponsitory.find(accountId);
            }

}
