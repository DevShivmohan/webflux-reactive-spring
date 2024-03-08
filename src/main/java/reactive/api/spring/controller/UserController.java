package reactive.api.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactive.api.spring.service.UserService;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Synchronous and blocking thread
     * It will return the all response when overall operation is done
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getAllUsersWithNormalRest(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }

    /**
     * This is reactive programming example using Flux
     * we have to return the response one by one in stream
     * It will handle by single thread
     * Asynchronous and non-blocking
     * @return
     */
    @GetMapping(value = "/flux",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<?> getAllUsersWithFlux(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAllUsersWithStream());
    }
}
