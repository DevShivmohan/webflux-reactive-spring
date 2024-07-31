package reactive.api.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactive.api.spring.model.User;
import reactive.api.spring.repository.UserRepository;
import reactive.api.spring.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.security.SecureRandom;
import java.util.List;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    /**
     * It will contain last one record in his cache
     */
    private final Sinks.Many<User> usersSink = Sinks.many().replay().limit(1);
    private final SecureRandom secureRandom=new SecureRandom();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers () {
        long startTime=System.currentTimeMillis();
        final List<User> users=userRepository.getAllUsers();
        long endTime=System.currentTimeMillis();
        log.info("Total normal Rest execution time - "+(endTime-startTime)+" ms");
        return users;
    }

    @Override
    public Flux<User> getAllUsersWithStream () {
        long startTime=System.currentTimeMillis();
        final Flux<User> users=userRepository.getAllUsersWithStream();
        long endTime=System.currentTimeMillis();
        log.info("Total Flux execution time - "+(endTime-startTime)+" ms");
        return users;
    }

    @Scheduled(fixedRate = 3 * 1000)
    public void sendUser() {
        final var result = usersSink.tryEmitNext(new User(secureRandom.nextInt(1000, 9999999), "Shivmohan"));
        log.info("SSE sent status {}", result);
    }

    @Override
    public Flux<User> getUsersWithSSEStream() {
        return usersSink.asFlux()
                .doOnSubscribe(subscription -> log.info("Subscription initiated..."));
    }

}
