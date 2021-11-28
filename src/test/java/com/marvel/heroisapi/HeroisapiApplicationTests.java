package com.marvel.heroisapi;

import com.marvel.heroisapi.controller.HeroiesController;
import com.marvel.heroisapi.repository.HeroesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import static com.marvel.heroisapi.constans.HeroesConstant.HEROES_ENDPOINT_LOCAL;
//import static jdk.internal.org.objectweb.asm.tree.AnnotationNode.accept;
//import org.springframework.test.web.reactive.server.DefaultWebTestClient;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroisapiApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroesRepository heroesRepository;
	/*
	@Autowired
	HeroesRepository heroesRepository;
	private DefaultWebTestClient.DefaultResponseSpec WebTestClientResponseSpec;

		private void exchange() {
	}

	 */

	@Test
	public void getOneHeroesById() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "3") capture of?
			.exchange() WebTestClientResponseSpec
			.expectStatus().isOk()
			.expectBody();
	}
	@Test
	public void getOneHeroNotFound(){
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "1")
			.exchange()
			.expectStatus().isNotFound();

	}
	@Test
	public void deleteHero(){
		webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10") capture of?
			.accept(MediaType.APPLICATION_JSON)
			.exchange() WebTestClientResponseSpec
			.expectStatus().isNotFound()
			.expectBody(Void.class);
	}


}
