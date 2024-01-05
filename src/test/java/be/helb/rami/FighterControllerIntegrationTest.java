    package be.helb.rami;


    import be.helb.arami.Models.Fighter;
    import io.restassured.RestAssured;
    import io.restassured.http.ContentType;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.springframework.http.HttpStatus;
    import org.springframework.test.context.junit.jupiter.SpringExtension;

    import static org.hamcrest.Matchers.equalTo;

    @ExtendWith(SpringExtension.class)
    public class FighterControllerIntegrationTest {

        @Test
        public void when_get_all_fighter(){ // un test simple
            RestAssured.with()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/fighter/all")
                    .then()
                    .statusCode(200);
        }

        @Test
        public void when_add_new_fighter() {
            Fighter createFighter = new Fighter("Jean","Louis", 45 , 175);

            RestAssured.given()
                    .body(createFighter).contentType(ContentType.JSON)
                    .when().request("POST", "/fighter/new")
                    .then().statusCode(201);
        }

        @Test
        void when_get_fighter_by_weight_category() {
            String weightCategoryName = "Lightweight";

            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/fighter/category/{weightCategoryName}", weightCategoryName)
                    .then()
                    .statusCode(200);
        }


        @Test
        void when_get_fighter_by_id_and_return_ok() {
            Long fighterId = 1L;

            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/fighter/{fighterId}", fighterId)
                    .then()
                    .statusCode(200);
        }

        @Test
        void when_get_fighter_by_id_and_return_not_found() {
            Long nonExistingFighterId = 999L;

            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/fighter/{fighterId}", nonExistingFighterId)
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value());
        }

        @Test
        void when_get_fighter_by_id_and_return_correct_fighter() {
            Long fighterId = 1L;

            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/fighter/{fighterId}", fighterId)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", equalTo(fighterId.intValue()));
        }

        @Test
        void when_update_fighter_and_return_ok() {
            Long fighterIdToUpdate = 25L;
            Fighter updatedFighter = new Fighter("TestFirstName", "TestLastName", 22, 222);

            RestAssured.given()
                    .body(updatedFighter)
                    .contentType(ContentType.JSON)
                    .when()
                    .put("/fighter/update/{fighterId}", fighterIdToUpdate)
                    .then()
                    .statusCode(HttpStatus.OK.value());
        }

        @Test
        void when_delete_fighter_and_return_ok() {
            Long fighterIdToDelete = 1L;

            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .delete("/fighter/delete/{fighterId}", fighterIdToDelete)
                    .then()
                    .statusCode(HttpStatus.OK.value());
        }


        @Test
        void when_get_oldest_fighter_and_return_ok() {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/fighters/oldest")
                    .then()
                    .statusCode(HttpStatus.OK.value());
        }

        @Test
        void when_get_youngest_fighter_and_return_ok() {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/fighters/youngest")
                    .then()
                    .statusCode(HttpStatus.OK.value());
        }

        @Test
        void when_get_tallest_fighter_and_return_ok() {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/fighters/tallest")
                    .then()
                    .statusCode(HttpStatus.OK.value());
        }

        @Test
        void when_get_shortest_fighter_and_return_ok() {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/fighters/shortest")
                    .then()
                    .statusCode(HttpStatus.OK.value());
        }

    }
