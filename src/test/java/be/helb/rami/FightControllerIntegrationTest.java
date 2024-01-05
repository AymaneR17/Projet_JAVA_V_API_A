package be.helb.rami;


import be.helb.arami.Models.Fight;
import be.helb.arami.Models.Fighter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class FightControllerIntegrationTest {

    @Test
    void when_create_fight_and_return_ok() {
        Fighter fighter1 = new Fighter();
        fighter1.setId(23L);
        fighter1.setFirstName("Combattant Test 1");
        fighter1.setAge(22);
        fighter1.setSize(180);
        Fighter fighter2 = new Fighter();
        fighter2.setId(24L);
        fighter2.setFirstName("Combattant Test 2");
        fighter2.setAge(22);
        fighter2.setSize(180);


        Fight newFight = new Fight(fighter1, fighter2, "Bruxelles", "Combattant 1");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(newFight)
                .when()
                .post("/fight/new")
                .then()
                .extract().response();

        response.then()
                .statusCode(HttpStatus.CREATED.value());
    }


    @Test
    void when_update_fight_and_return_ok() {
        Long fightId = 8L;

        Fighter updatedFighter1 = new Fighter();
        updatedFighter1.setId(23L);
        updatedFighter1.setFirstName("Updated Fighter 1");
        updatedFighter1.setAge(25);
        updatedFighter1.setSize(185);

        Fighter updatedFighter2 = new Fighter();
        updatedFighter2.setId(24L);
        updatedFighter2.setFirstName("Updated Fighter 2");
        updatedFighter2.setAge(26);
        updatedFighter2.setSize(190);

        Fight updatedFight = new Fight(updatedFighter1, updatedFighter2, "Updated Place", "Updated Fighter 1");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(updatedFight)
                .when()
                .put("/fight/update/{fightId}", fightId)
                .then()
                .extract().response();

        response.then()
                .statusCode(HttpStatus.OK.value());

    }


    @Test
    void when_delete_fight_and_return_ok() {
        Long existingFightId = 8L;

        String response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/fight/delete/{fightId}", existingFightId)
                .then()
                .statusCode(200)
                .extract().asString();

        System.out.println("Response: " + response);

    }


}
