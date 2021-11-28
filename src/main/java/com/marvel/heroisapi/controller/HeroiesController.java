package com.marvel.heroisapi.controller;

import com.amazonaws.services.dynamodbv2.xspec.M;
import com.marvel.heroisapi.document.Heroes;
import com.marvel.heroisapi.repository.HeroesRepository;
import com.marvel.heroisapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.marvel.heroisapi.constans.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j
public class HeroiesController {

    HeroesService heroesService;
    HeroesRepository heroesRepository;

    private static final org.slf4j.Logger logs = org.slf4j.LoggerFactory.getLogger(HeroiesController.class);

    public HeroiesController (HeroesService heroesService, HeroesRepository heroesRepository){
        this.heroesRepository=heroesRepository;
        this.heroesService=heroesService;
    }
    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItems(){
        logs.info("requesting the list off all heroes");
        return heroesService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL + "/id")
    public Mono <ResponseEntity<Heroes>> findByHero(@PathVariable String id) {
        log.info("requesting the hero with id {}", id);
        return heroesService.findByIdHero(id)
                .map((item) -> new ResponseEntity<>(item,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes){
        log.info("a new hero was acreated");
        return heroesService.save(heroes);
    }
    @PostMapping(HEROES_ENDPOINT_LOCAL + "/id")
    @ResponseStatus(code = HttpStatus.CONTINUE)
    public Mono<HttpStatus> deletbyIDHero(@PathVariable String id) {
        heroesService.deleteByIdHero(id);
        return Mono.just(HttpStatus.CONTINUE);
    }


}





