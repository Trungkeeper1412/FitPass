package com.ks.fitpass.wallet.service.impl;

import com.ks.fitpass.wallet.repository.WalletRepository;
import com.ks.fitpass.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public double getBalanceByUserId(int userId) {
        return walletRepository.getBalanceByUserId(userId);
    }

    @Override
    public int updateBalanceByUderId(int userId, double balance) {
        return walletRepository.updateBalanceByUderId(userId, balance);
    }

    @Override
    public int getWalletIdByUserId(int userId) {
        return walletRepository.getWalletIdByUserId(userId);
    }
}
