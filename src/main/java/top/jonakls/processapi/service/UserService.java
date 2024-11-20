package top.jonakls.processapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jonakls.processapi.entity.UserEntity;
import top.jonakls.processapi.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public UserEntity addUser(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }

    public UserEntity updateUser(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }

    public void deleteUser(int id) {
        this.userRepository.deleteById(id);
    }

    public UserEntity getUser(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public UserEntity getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public boolean isUserExist(String email) {
        return this.userRepository.findByEmail(email) != null;
    }

    public List<UserEntity> getUsers() {
        return this.userRepository.findAll();
    }
}
