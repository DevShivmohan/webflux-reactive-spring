package reactive.api.spring.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactive.api.spring.model.User;
import reactive.api.spring.repository.UserRepository;
import reactive.api.spring.service.UserService;
import reactor.core.publisher.Flux;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<User> getAllUsers () {
        long startTime=System.currentTimeMillis();
        final List<User> users=userRepository.getAllUsers();
        long endTime=System.currentTimeMillis();
        log.info("Total execution time - "+(endTime-startTime)+" ms");
        return users;
    }

    @Override
    public Flux<User> getAllUsersWithStream () {
        long startTime=System.currentTimeMillis();
        final Flux<User> users=userRepository.getAllUsersWithStream();
        long endTime=System.currentTimeMillis();
        log.info("Total execution time - "+(endTime-startTime)+" ms");
        return users;
    }

}
