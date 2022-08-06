package br.com.passaporteclio.passaporteclio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PassaporteclioApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertTrue(true);
	}

}
