package entities.user;

import common.SystemErrors;

public class UserValidator {
    public static void validateUsername(String name) {
        if (name.length() < 5 || name.chars().noneMatch(Character::isDigit)) {
            throw new IllegalArgumentException(SystemErrors.INVALID_USERNAME);
        }
    }

    public static void validatePassword(String password) {
        if (password.isBlank() || password.length() != 6 || password.chars().anyMatch(Character::isLetter)) {
            throw new IllegalArgumentException(SystemErrors.INVALID_PASSWORD);
        }
    }
}
