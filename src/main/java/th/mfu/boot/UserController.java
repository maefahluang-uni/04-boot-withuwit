package th.mfu.boot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    public static Map<String, User> users = new HashMap<String, User>();

    @PostMapping
    public ResponseEntity<String> registerUser( User user) {
      //TODO
    if (users.containsKey(user.getUsername())) {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }
        users.put(user.getUsername(), user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Collection<User>> list() {
        //TODO
        return new ResponseEntity<>(users.values(), HttpStatus.OK);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(String username) {
        //TODO
        User user = users.get(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
}
