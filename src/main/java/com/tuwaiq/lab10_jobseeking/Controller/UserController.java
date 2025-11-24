package com.tuwaiq.lab10_jobseeking.Controller;

import Api.ApiResponse;
import com.tuwaiq.lab10_jobseeking.Models.User;
import com.tuwaiq.lab10_jobseeking.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getUsers(){
        List<User> users= userService.getUsers();
        if (users.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no users to show"));
        }else {
            return ResponseEntity.status(200).body(users);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }else {
            userService.addUser(user);
            return ResponseEntity.status(200).body(new ApiResponse("The user have been added successfully"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(Integer id, User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (userService.updateUser(id,user)){
            return ResponseEntity.status(200).body(new ApiResponse("The user have been updated successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("There were no users with this id found"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(Integer id){
        if (userService.deleteUser(id)){
            return ResponseEntity.status(200).body(new ApiResponse("The user have been deleted successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("There were non users with this id found"));
        }
    }

}
