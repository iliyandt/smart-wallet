package entities.wallet;

import java.util.Currency;
import java.util.UUID;

public class DisposableWallet extends Wallet{

    private int withdrawAttempts;

    public DisposableWallet(UUID ownerId, String ownerUsername, Currency currency) {
        super(ownerId, ownerUsername, currency, 0);
    }


    @Override
    public void withdraw(double amount) {

        if (withdrawAttempts >= 2) {
            setStatus(WalletStatus.INACTIVE);
        }

        super.withdraw(amount);
        this.withdrawAttempts++;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(System.lineSeparator());

        builder.append("Max withdrawals: ").append(2)
                .append(System.lineSeparator());
        builder.append("Current withdrawals: ").append(withdrawAttempts);

        return builder.toString();

    }
}
