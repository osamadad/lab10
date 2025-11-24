package com.tuwaiq.lab10_jobseeking.Service;

import com.tuwaiq.lab10_jobseeking.Models.User;
import com.tuwaiq.lab10_jobseeking.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<?> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id, User user) {
        User oldUser = userRepository.getById(id);
        if (oldUser == null) {
            return false;
        } else {
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setAge(user.getAge());
            oldUser.setRole(user.getRole());
            userRepository.save(oldUser);
            return true;
        }
    }

    public Boolean deleteUser(Integer id) {
        User user = userRepository.getById(id);
        if (user == null) {
            return false;
        } else {
            userRepository.delete(user);
            return true;
        }
    }

    public Boolean checkUserMatchApplication(Integer userId, Integer applicationUserId){
        if (userId.equals(applicationUserId)){
            return true;
        }else {
            return false;
        }
    }

    public User checkUserExist(Integer UserId) {
        for (User user : userRepository.findAll()) {
            if (user.getId().equals(UserId)) {
                return user;
            }
        }
        return null;
    }

    public Boolean checkUserRole(User user, String role) {
        if (user.getRole().equalsIgnoreCase(role)) {
            return true;
        } else {
            return false;
        }
    }
}
