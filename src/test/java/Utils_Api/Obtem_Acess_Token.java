package R2D2_Api_Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;

public class Obtem_Acess_Token {

    private static String token;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://TradeTech.R2D2.api";
    }

    public static String getToken() {
        // Verifica se o token já foi obtido para evitar chamadas desnecessárias
        if (token == null) {
            String username = "Obi-wan";
            String password = "maytheforcebewithyou";

            token = given()
                    .contentType(ContentType.JSON)
                    .body("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }")
                    .when()
                    .post("/authenticate")
                    .then()
                    .statusCode(200)
                    .body("token", not(null))
                    .extract()
                    .path("token");
        }

        return token;
    }
}