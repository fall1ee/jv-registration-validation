package core.basesyntax.service;

import static core.basesyntax.db.Storage.people;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.expection.AlreadyExistException;
import core.basesyntax.expection.InvalidDataException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int MIN_AGE = 18;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MIN_LOGIN_LENGTH = 6;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {

        if (user == null) {
            throw new InvalidDataException("User cannot be null");
        }

        if (user.getLogin() == null
                || user.getLogin().length() < MIN_LOGIN_LENGTH
                || user.getPassword() == null
                || user.getPassword().length() < MIN_PASSWORD_LENGTH
                || user.getAge() == 0
                || user.getAge() < MIN_AGE) {
            throw new InvalidDataException("Invalid data typed");
        }

        for (User users : people) {
            if (user.getLogin().equals(users.getLogin())) {
                throw new AlreadyExistException("This login already exists");
            }
        }

        return storageDao.add(user);
    }
}
