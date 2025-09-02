package th.mfu.boot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    public static Map<String, User> users = new HashMap<>();

    // POST /users
    @PostMapping("/users")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (users.containsKey(user.getUsername())) {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }
        users.put(user.getUsername(), user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    // GET /users
    @GetMapping("/users")
    public ResponseEntity<Collection<User>> list() {
        return new ResponseEntity<>(users.values(), HttpStatus.OK);
    }

    // GET /users/{username}
    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = users.get(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
