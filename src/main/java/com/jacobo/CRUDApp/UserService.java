package com.jacobo.CRUDApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<User> listAll(){
        return repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException{
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID " + id);
    }
}
