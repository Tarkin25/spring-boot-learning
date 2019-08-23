package ch.noseryoung.learning.domain.greeting;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/greeting", "", "/"})
public class GreetingController {

    @GetMapping
    public ResponseEntity<String> greeting(@RequestParam("name") String name) {
        String message;

        if(name != null) {
            message = String.format("Greetings, %s!", name);
        } else {
            message = "Greetings!";
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
