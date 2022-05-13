package nus.iss.paf.miniproject.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.paf.miniproject.models.User;
import nus.iss.paf.miniproject.repositories.UsersRepositories;

@Service
public class UsersService {

    @Autowired
    private UsersRepositories usersRepo;

    public boolean authenticate(String name, String email, String password) {
        return 1 == usersRepo.countUsersByNameAndPassword(name, email, password);
    }

    public void addNewUser(User user) throws UserException {
        Optional<User> opt = usersRepo.findUserByEmail(user.getEmail());
        if (opt.isPresent()) {
            throw new UserException("%s is already registered in the system.".formatted(user.getName()));
        } else {
            usersRepo.insertUser(user);
        }
    
}
}
