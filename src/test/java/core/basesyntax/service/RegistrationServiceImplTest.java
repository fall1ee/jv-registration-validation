package core.basesyntax.service;

import static core.basesyntax.db.Storage.people;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import core.basesyntax.expection.AlreadyExistException;
import core.basesyntax.expection.InvalidDataException;
import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistrationServiceImplTest {
    private final RegistrationService registrationService = new RegistrationServiceImpl();
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setLogin("login123");
        user.setId(1L);
        user.setAge(20);
        user.setPassword("321883218321");
        people.clear();
    }

    @Test
    void userExist_NoOk() {
        registrationService.register(user);
        try {
            registrationService.register(user);
        } catch (AlreadyExistException e) {
            return;
        }
        fail("AlreadyExistException should be thrown if user exists");
    }

    @Test
    void userNotAddedBecauseOfAge_NoOk() {
        user.setAge(10);
        try {
            registrationService.register(user);
        } catch (InvalidDataException e) {
            return;
        }
        fail("InvalidDataException should be thrown if user data wrong is");
    }

    @Test
    void userNotAddedBecauseOfLogin_NoOk() {
        user.setLogin("ew");
        try {
            registrationService.register(user);
        } catch (InvalidDataException e) {
            return;
        }
        fail("InvalidDataException should be thrown if user data wrong is");
    }

    @Test
    void userNotAddedBecauseOfPassword_NoOk() {
        user.setPassword("2941");
        try {
            registrationService.register(user);
        } catch (InvalidDataException e) {
            return;
        }
        fail("InvalidDataException should be thrown if user data wrong is");
    }

    @Test
    void userAdded_Ok() {
        User result = registrationService.register(user);
        assertNotNull(result);
    }

    @Test
    void userDataIsNull_NoOk() {
        user = null;

        try {
            registrationService.register(user);
        } catch (InvalidDataException e) {
            return;
        }

        fail("InvalidDataException should be thrown");
    }
}
