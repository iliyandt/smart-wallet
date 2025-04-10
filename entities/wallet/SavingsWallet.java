package entities.wallet;

import common.SystemErrors;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

public class SavingsWallet extends Wallet{

    private LocalDateTime savingPeriodEnd;

    public SavingsWallet(UUID ownerId, String ownerUsername, Currency currency) {
        super(ownerId, ownerUsername, currency, 10);
        this.savingPeriodEnd = LocalDateTime.now().plusMinutes(2);
    }


    @Override
    public void withdraw(double amount) {
        if (LocalDateTime.now().isBefore(savingPeriodEnd)) {
            long leftSeconds = Duration.between(LocalDateTime.now(), savingPeriodEnd).toSeconds();
            throw new IllegalArgumentException(SystemErrors.SAVINGS_PERIOD_NOT_CONCLUDED_YET.formatted(leftSeconds));
        }
        super.withdraw(amount);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        long leftSeconds = Duration.between(LocalDateTime.now(), savingPeriodEnd).toSeconds();

        builder.append(System.lineSeparator());
        builder.append("Saving period ends within: ")
                .append(Math.max(0, leftSeconds)).append(" seconds");
        return builder.toString();
    }
}
