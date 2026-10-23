package core.basesyntax.service;

import static core.basesyntax.db.Storage.people;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.expection.AlreadyExistException;
import core.basesyntax.expection.InvalidDataException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {

        if (user == null) {
            throw new InvalidDataException("User cannot be null");
        }

        if (user.getLogin() == null
                || user.getLogin().length() < 6
                || user.getPassword() == null
                || user.getPassword().length() < 6
                || user.getAge() < 18) {
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
