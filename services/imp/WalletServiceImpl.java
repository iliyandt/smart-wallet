package services.imp;

import core.UserSessionManager;
import repositories.WalletRepository;
import services.WalletService;

import java.util.Currency;
import java.util.UUID;

public class WalletServiceImpl implements WalletService {

    private UserSessionManager sessionManager;
    private WalletRepository walletRepository;

    public WalletServiceImpl(UserSessionManager sessionManager) {
        this.sessionManager = sessionManager;
        this.walletRepository = new WalletRepository();
    }

    @Override
    public String createNewWallet(Currency currency, String walletType) {
        return "";
    }

    @Override
    public String getMyWallets() {
        return "";
    }

    @Override
    public String deposit(UUID walletId, double amount) {
        return "";
    }

    @Override
    public String transfer(UUID walletId, String receiverUsername, double amount) {
        return "";
    }

    @Override
    public String changeWalletStatus(UUID walletId, String newStatus) {
        return "";
    }
}
