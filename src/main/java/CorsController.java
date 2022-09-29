import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping
public class CorsController {
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        String hello = "HELLO!";
        return new ResponseEntity<>(hello, HttpStatus.OK);
    }
}
