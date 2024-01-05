package be.helb.rami;


import be.helb.arami.Models.WeightCategory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
public class WeightCategoryControllerIntegrationTest {

    @Test
    public void when_add_new_weight_category() {
        WeightCategory newWeightCategory  = new WeightCategory();

        newWeightCategory.setNameWeightCategory("Nouvelle categorie de poids");

        RestAssured.given()
                .body(newWeightCategory).contentType(ContentType.JSON)
                .when().request("POST", "/weightcategory/new")
                .then()
                .statusCode(201);
    }


    @Test
    public void when_get_all_weight_category_and_return_ok(){
        RestAssured.with()
                .contentType(ContentType.JSON)
                .when()
                .get("/weightcategory/all")
                .then()
                .statusCode(200);
    }


    @Test
    public void when_get_weight_category_with_most_fighters() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/weightcategory/most-fighters")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("nameWeightCategory", notNullValue());
    }

    @Test
    public void when_get_weight_category_with_least_fighters() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/weightcategory/least-fighters")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("nameWeightCategory", notNullValue());
    }


    @Test
    public void when_delete_weight_category() {
        Long weightCategoryIdToDelete =10L;

        String response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete("weightcategory/delete/{weightCategoryId}", weightCategoryIdToDelete)
                .then()
                .statusCode(200)
                .extract().asString();

        System.out.println("Response: " + response);
    }
}
