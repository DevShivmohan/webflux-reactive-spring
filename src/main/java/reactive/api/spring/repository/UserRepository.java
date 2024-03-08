package reactive.api.spring.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactive.api.spring.model.User;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
public class UserRepository {
    public static void sleepExecution(int count) {
        try {
            System.out.println("Sleep count time "+count);
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e) {
            log.error(e.toString());
        }
    }

    /**
     * Synchronous and blocking thread
     * It will return the all response when overall operation is done
     * @return
     */
    public List<User> getAllUsers(){
        return IntStream.range(1,50)
                .peek(UserRepository::sleepExecution)
                .mapToObj(count->new User(count,"Shivmohan"+count))
                .collect(Collectors.toList());
    }

    /**
     * This is reactive programming example using Flux
     * we have to return the response one by one in stream
     * It will handle by single thread
     * Asynchronous and non-blocking
     * @return
     */
    public Flux<User> getAllUsersWithStream(){
        return Flux.range(1,50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(count->System.out.println("Streaming count - "+count))
                .map(number->new User(number,"Shivmohan"+number));
    }
}
