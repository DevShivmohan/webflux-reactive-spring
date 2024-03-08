package reactive.api.spring.service;

import reactive.api.spring.model.User;
import reactor.core.publisher.Flux;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    Flux<User> getAllUsersWithStream();
}
