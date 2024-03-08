package reactive.api.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class ReactiveApiSpringApplicationTests {

	@Test
	void testMono() {
		Mono<?> monoGeneric= Mono.just("Shivmohan")
				.log();
		monoGeneric.subscribe(System.out::println);
	}

	@Test
	void testFlux() {
		Flux<?> fluxGeneric= Flux.just()
				.log();
		fluxGeneric.subscribe(System.out::println);
	}

}
