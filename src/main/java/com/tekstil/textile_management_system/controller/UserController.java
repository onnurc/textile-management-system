package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.Role;
import com.tekstil.textile_management_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> Users = userService.getAllUsers();
        return ResponseEntity.ok(Users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/role/{role}")
    public ResponseEntity <List<User>> getUserByRole(@PathVariable Role role){
        List<User> user = userService.getUsersByRole(role);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/active")
    public ResponseEntity <List<User>> getUserByActive(){
        List<User> user = userService.findByActive();
        return ResponseEntity.ok(user);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<List<User>> findByEmail(@PathVariable String email ){
        List<User> emails = userService.findByEmail(email);
        return ResponseEntity.ok(emails);

    }
    @GetMapping("/created-after")
    ResponseEntity<List<User>> findByCreatedAtAfter(@RequestParam LocalDateTime localDateTime){
        List<User> users = userService.findByCreatedAtAfter(localDateTime);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<User>> findByFullNameContaining(@PathVariable String keyword){
        List <User> users = userService.findByFullNameContainingIgnoreCase(keyword);
        return ResponseEntity.ok(users);
    }
    //get is ready  continue tomorrow

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id , @Valid @RequestBody User user){
        User updated = userService.updateUser(id,user);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }





}
