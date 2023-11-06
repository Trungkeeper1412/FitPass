package com.ks.fitpass.wallet.repository;


public interface WalletRepository {
    double getBalanceByUserId(int userId);

    int updateBalanceByUderId(int userId, double balance);

    int getWalletIdByUserId(int userId);
}
