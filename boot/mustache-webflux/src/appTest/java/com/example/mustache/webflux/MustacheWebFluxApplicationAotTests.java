package com.example.mustache.webflux;

import org.junit.jupiter.api.Test;

import org.springframework.aot.smoketest.support.junit.ApplicationTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationTest
class MustacheWebFluxApplicationAotTests {

	@Test
	void greetingIsRendered(WebTestClient client) {
		client.get().uri("/greeting").exchange().expectStatus().isOk().expectBody().consumeWith(
				(result) -> assertThat(new String(result.getResponseBodyContent())).contains("Hello world"));
	}

	@Test
	void authorListIsRendered(WebTestClient client) {
		client.get().uri("/authors").exchange().expectStatus().isOk().expectBody()
				.consumeWith((result) -> assertThat(new String(result.getResponseBodyContent()))
						.contains("<li>Brian Goetz</li>").contains("<li>Joshua Bloch</li>"));
	}

}
