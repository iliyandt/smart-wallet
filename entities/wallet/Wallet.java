package entities.wallet;

import common.SystemErrors;

import java.util.Currency;
import java.util.UUID;

public abstract class Wallet {
    private UUID id;
    private UUID ownerId;
    private String ownerUsername;
    private Currency currency;
    private double balance;
    private WalletStatus status;


    public Wallet(UUID ownerId, String ownerUsername, Currency currency, double balance) {
        this.id = UUID.randomUUID();
        this.ownerId = ownerId;
        this.ownerUsername = ownerUsername;
        this.currency = currency;
        this.balance = balance;
        status = WalletStatus.ACTIVE;
    }

    public void deposit(double amount) {
        if (status == WalletStatus.INACTIVE || amount < 0) {
            throw new IllegalArgumentException(SystemErrors.NO_OPERATIONS_ALLOWED_FOR_NON_ACTIVE_WALLET);
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (status == WalletStatus.INACTIVE || amount < 0) {
            throw new IllegalArgumentException(SystemErrors.NO_OPERATIONS_ALLOWED_FOR_NON_ACTIVE_WALLET);
        }

        if (balance < amount) {
            throw new IllegalArgumentException(SystemErrors.INSUFFICIENT_FUNDS_IN_WALLET);
        }

        this.balance -= amount;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }


    public String getOwnerUsername() {
        return ownerUsername;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public WalletStatus getStatus() {
        return status;
    }

    public void setStatus(WalletStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Wallet: [%s][%s]:".formatted(id, this.getClass().getSimpleName()))
                .append(System.lineSeparator());
        builder.append("Owner: ").append(ownerUsername)
                .append(System.lineSeparator());
        builder.append("Currency: ").append(currency)
                .append(System.lineSeparator());
        builder.append("Balance: ").append(balance)
                .append(System.lineSeparator());
        builder.append("Status: ").append(status)
                .append(System.lineSeparator());

        return builder.toString();
    }
}
