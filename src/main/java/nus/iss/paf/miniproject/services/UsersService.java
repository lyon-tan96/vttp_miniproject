package nus.iss.paf.miniproject.services;

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

    public void addNewUser(User user) {
        if(!usersRepo.insertUser(user)) 
            throw new IllegalArgumentException("Cannot add %s new user. Please contact Admin.".formatted(user.getName()));
    }
    
}
