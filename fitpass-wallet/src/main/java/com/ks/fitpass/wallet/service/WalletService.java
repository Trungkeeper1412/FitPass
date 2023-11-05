package com.ks.fitpass.wallet.service;

public interface WalletService {
    double getBalanceByUserId(int userId);

    int updateBalanceByUderId(int userId, double balance);
    int getWalletIdByUserId(int userId);

}
