package edu.study.pasudo123.app.account.repository;

/**
 * Created by pasudo123 on 2020-01-04
 * Email: oraedoa@gmail.com
 **/
public class AccountContext {

    private static final ThreadLocal<Account> ACCOUNT_THREAD_LOACL
            = new ThreadLocal<>();

    public static void setAccount(final Account account) {
        ACCOUNT_THREAD_LOACL.set(account);
    }

    public static Account getAccount() {
        return ACCOUNT_THREAD_LOACL.get();
    }
}
