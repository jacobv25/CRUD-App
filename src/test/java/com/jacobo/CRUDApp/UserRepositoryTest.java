package com.jacobo.CRUDApp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
//The test will be executed against the real database instead of the database in memory
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//Keep data committed to the database
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("jamie21@gmail.com");
        user.setPassword("pass123");
        user.setFirstName("jamie");
        user.setLastName("Doober");

        User savedUser = repo.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer userId1 = 1;
        Optional<User> optionalUser = repo.findById(userId1);
        User user = optionalUser.get();
        user.setPassword("hello2000");
        repo.save(user);

        User updatedUser = repo.findById(userId1).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("hello2000");
    }

    @Test
    public void testGet() {
        Integer userId1 = 2;
        Optional<User> optionalUser = repo.findById(userId1);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());

    }

    @Test
    public void testDelete(){
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
