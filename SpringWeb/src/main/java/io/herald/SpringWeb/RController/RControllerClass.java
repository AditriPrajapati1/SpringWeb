package io.herald.SpringWeb.RController;

import io.herald.SpringWeb.Model.UserTable;
import io.herald.SpringWeb.Repository.ImageRepository;
import io.herald.SpringWeb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RControllerClass
{
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello()
    {
        return "Hello World";
    }

    @GetMapping("/getAllUsers")
    public List<UserTable> getAllUsers()
    {
        return userRepository.findAll();
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestBody UserTable user)
    {
        //@RequestBody --> JSON ma data aako cha bhane, requestbody lekhna parcha

        userRepository.save(user);
        return "Saved Successfully";
    }

    @GetMapping("/getOne/{id}")  //Path variable
    public UserTable getOne(@PathVariable int id)
    {
        UserTable u = userRepository.findById(id).get();
        return u;
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<?> getId(@PathVariable int id)
    {
        if(userRepository.findById(id).isPresent())
        {
            return ResponseEntity.ok(userRepository.findById(id).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID NOT FOUND");
    }

}
