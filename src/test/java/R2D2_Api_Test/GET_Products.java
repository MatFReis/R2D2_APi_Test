package R2D2_Api_Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GET_Products {

    private static String token;

    @BeforeAll
    public static void setup() {
        // Obtenha o token antes de executar os testes
        token = R2D2_Api_Test.Obtem_Acess_Token.getToken();
        RestAssured.baseURI = "https://TradeTech.R2D2.api";
    }

    @Test
    public void Devo_Obter_Lista_De_Produtos() {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200);
    }

    @Test
    public void Devo_Obter_Produto_Por_ID() {
        int productId = 123;

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/products/{id}", productId)
                .then()
                .statusCode(200)
                .body("id", equalTo(productId))
                .body("name", equalTo("Nome do Produto"))
                .body("price", equalTo(19.99));
    }

    @Test
    public void Devo_Tentar_Obter_Produto_Inexistente_Por_iD() {
        int nonExistingProductId = 999;

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/products/{id}", nonExistingProductId)
                .then()
                .statusCode(404)
                .body("message", equalTo("Product not found"));
    }
}
