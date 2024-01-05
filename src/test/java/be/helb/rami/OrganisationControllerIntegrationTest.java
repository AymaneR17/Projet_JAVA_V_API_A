package be.helb.rami;

import be.helb.arami.Models.Organisation;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.notNullValue;


@ExtendWith(SpringExtension.class)
public class OrganisationControllerIntegrationTest {

        @Test
        public void when_get_all_organisation(){
            RestAssured.with()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/organisation/all")
                    .then()
                    .statusCode(200);
        }


    @Test
    public void when_add_new_organisation() {
        Organisation newOrganisation = new Organisation();

        newOrganisation.setNameOrganisation("Nouvelle organisation");

        RestAssured.given()
                .body(newOrganisation).contentType(ContentType.JSON)
                .when().request("POST", "/organisation/new")
                .then()
                .statusCode(201);
    }
    @Test
    public void when_update_organisation() {
        Organisation updatedOrganisation = new Organisation();
        updatedOrganisation.setId(1L);
        updatedOrganisation.setNameOrganisation("KSW");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(updatedOrganisation)
                .when()
                .put("organisation/update/{organisationId}", updatedOrganisation.getId())
                .then()
                .statusCode(200);
    }


    @Test
    public void when_get_organisation_with_most_fighters() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/organisation/most-fighters")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("name", notNullValue());
    }


    @Test
    public void when_delete_organisation() {
        Long organisationIdToDelete = 1L;

        String response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete("organisation/delete/{organisationId}", organisationIdToDelete)
                .then()
                .statusCode(200)
                .extract().asString();

        System.out.println("Response: " + response);
    }


}
