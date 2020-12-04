//package org.zoho.zlabs.webCLI.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.zoho.zlabs.webCLI.model.User;
//import org.zoho.zlabs.webCLI.service.UserService;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/getAll")
//    public ResponseEntity<List<User>> getAllUsers(){
//        return ResponseEntity.ok().body(userService.getAllUsers());
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<String> saveUser(@RequestBody @Valid User user){
//        return ResponseEntity.ok().body(userService.saveUser(user));
//    }
//
//    @GetMapping("/get")
//    public Optional<User> getUser(@RequestBody String username){
//        return userService.getUserById(username);
//    }
//
//    @PostMapping("/delete")
//    public ResponseEntity<String> deleteUser(@RequestBody String username){
//        return ResponseEntity.ok().body(userService.deleteUserbyId(username));
//    }
//}
