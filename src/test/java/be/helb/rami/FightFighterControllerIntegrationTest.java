package be.helb.rami;

import be.helb.arami.Models.Fight;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class FightFighterControllerIntegrationTest {


    @Test
    void when_get_fights_by_fighter_and_return_ok() {
        Long fighterId = 21L;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/fight-fighter/{fighterId}", fighterId)
                .then()
                .extract().response();

        response.then()
                .statusCode(HttpStatus.OK.value());

        List<Fight> fights = response.jsonPath().getList(".", Fight.class);
        assertNotNull(fights);
        assertTrue(fights.size() > 0);
    }

    @Test
    void when_get_fights_by_nonexistent_fighter_and_return_not_found() {
        Long nonexistentFighterId = 999L;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/fight-fighter/{fighterId}", nonexistentFighterId)
                .then()
                .extract().response();

        response.then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
