package services.imp;

import common.LogMessages;
import common.SystemErrors;
import core.UserSessionManager;
import entities.user.User;
import repositories.UserRepository;
import services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserSessionManager sessionManager;
    private UserRepository userRepository;

    public UserServiceImpl(UserSessionManager sessionManager) {
        this.sessionManager = sessionManager;
        this.userRepository = new UserRepository();
    }

    @Override
    public String login(String username, String password) {

        if (sessionManager.hasActiveSession()) {
            throw new IllegalArgumentException(SystemErrors.USER_ALREADY_LOGGED_IN.formatted(sessionManager.getActiveSession().getUsername()));
        }

        User user = userRepository.getAll().stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() ->  new IllegalArgumentException(SystemErrors.INCORRECT_LOGIN_CREDENTIALS));

        sessionManager.setActiveSession(user);

        return LogMessages.SUCCESSFULLY_LOGGED_IN.formatted(user.getUsername());
    }

    @Override
    public String register(String username, String password) {
        boolean isUsernameAlreadyExist = userRepository.getAll().stream()
                .anyMatch(u -> u.getUsername().equals(username));

        if (isUsernameAlreadyExist) {
            throw new IllegalArgumentException(SystemErrors.SUCH_USERNAME_ALREADY_EXIST.formatted(username));
        }

        User user = new User(username, password);
        userRepository.save(user.getId(), user);
        login(username, password);

        return LogMessages.SUCCESSFULLY_REGISTERED.formatted(username);
    }

    @Override
    public String logout() {
        if (!sessionManager.hasActiveSession()) {
            throw new IllegalArgumentException(SystemErrors.NO_ACTIVE_USER_SESSION_FOUND);
        }

        sessionManager.terminateActiveSession();

        return LogMessages.SUCCESSFULLY_LOGGED_OUT.formatted(sessionManager.getActiveSession().getUsername());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }
}
