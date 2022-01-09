package com.teedjay;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PokemonResourceTest {

    @Test
    public void allPokemons() {
        given()
          .when().get("/pokemon")
          .then()
             .statusCode(200)
             .body(is("""
                 [{"id":1,"num":"001","name":"Bulbasaur"},{"id":2,"num":"002","name":"Ivysaur"}]"""))
        ;
    }

    @Test
    public void secondPokemon() {
        given()
          .when().get("/pokemon/2")
          .then()
            .statusCode(200)
            .body(is("""
                {"id":2,"num":"002","name":"Ivysaur"}"""))
        ;
    }

}
