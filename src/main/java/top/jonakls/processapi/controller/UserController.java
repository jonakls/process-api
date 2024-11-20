package top.jonakls.processapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.jonakls.processapi.entity.UserEntity;
import top.jonakls.processapi.service.UserService;
import top.jonakls.processapi.util.PasswordUtil;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @GetMapping("/hello-world")
    public ResponseEntity<?> helloWorld() {
        return ResponseEntity.ok("Hello World, i'm a user API :)");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserByEmail(@RequestBody final String email) {
        return ResponseEntity.ok(this.userService.getUserByEmail(email));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable final int id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody final UserEntity userEntity) {

        if (this.userService.isUserExist(userEntity.getEmail())) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        String password = userEntity.getPassword();
        if (password.length() < 8) {
            return ResponseEntity.badRequest().body("Password must be at least 8 characters long");
        }

        password = PasswordUtil.hash(password);
        userEntity.setPassword(password);
        return ResponseEntity.ok(this.userService.addUser(userEntity));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody final UserEntity userEntity) {
        String password = userEntity.getPassword();
        if (password.length() < 8) {
            return ResponseEntity.badRequest().body("Password must be at least 8 characters long");
        }

        password = PasswordUtil.hash(password);
        userEntity.setPassword(password);
        return ResponseEntity.ok(this.userService.updateUser(userEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable final int id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final UserEntity userEntity) {
        final UserEntity user = this.userService.getUserByEmail(userEntity.getEmail());
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (!PasswordUtil.comparePassword(userEntity.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }

        return ResponseEntity.ok(user);
    }
}
