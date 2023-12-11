package R2D2_Api_Test;

import Utils_Api.Dados_De_Produtos;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static R2D2_Api_Test.Obtem_Acess_Token.getToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class POST_Products {

    private static String token;


    @BeforeAll
    public static void setup() {
        String token = getToken();
        RestAssured.baseURI = "https://TradeTech.R2D2.api";
    }

    @Test
    public void Devo_Criar_Novo_Produto_Com_Sucesso() {
      //Dados do produto estão no APi.Utils "Dados_De_Produtos"
        // Visando a otimização do código.

        String token = getToken();

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(Dados_De_Produtos.getProductRequestBody())
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                    .body("nome", equalTo("Produto Teste"))
                    .body("categoria", equalTo("Enlatados"))
                    .body("unidade", equalTo("ml"))
                    .body("toggleStatus", equalTo(true))
                    .body("valorEmCreditos", equalTo(100))
                    .body("valorDescontoSalarial", equalTo(10.50))
                    .body("kcal", equalTo(150.75));
    }


    @Test
    public void Devo_Validar_Campos_Obrigatórios(){
            String token = getToken();
            String requestBody = "{"
                    + "\"nome\": null,"
                    + "\"categoria\": null,"
                    + "\"unidade\": null,"
                    + "\"valorEmCreditos\": null,"
                    + "\"valorDescontoSalarial\": null"
                    + "}";

            given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                        .post("/api/products")
                        .then()
                        .statusCode(400)
                        .body("mensagem", equalTo("Campos obrigatórios não podem ser nulos ou vazios"))
                        .body("detalhes[0].campo", equalTo("nome"))
                        .body("detalhes[1].campo", equalTo("categoria"))
                        .body("detalhes[2].campo", equalTo("unidade"))
                        .body("detalhes[3].campo", equalTo("valorEmCreditos"))
                        .body("detalhes[4].campo", equalTo("valorDescontoSalarial"));
        }

        @Test
        public void Devo_Validar_Campos_nicos(){
        String token = getToken();
        String nomeProduto = "ProdutoUnico";

        // Cria o produto com um nome específico
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{"
                              + "\"nome\": \"Chocolate ABC","
                              + "\"categoria\": \"Bebidas\","
                              + "\"unidade\": \"Unidade\","
                              + "\"valorEmCreditos\": 100,"
                              + "\"valorDescontoSalarial\": 10.50,"
                              + "\"kcal\": 150.75"
                              + "}")
            .post("/api/products")
            .then()
                .statusCode(201);

        // Tenta criar outro produto com o mesmo nome
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{"
                              + "\"nome\": \"Chocolate ABC","
                              + "\"categoria\": \"OutraCategoria\","
                              + "\"unidade\": \"OutraUnidade\","
                              + "\"valorEmCreditos\": 200,"
                              + "\"valorDescontoSalarial\": 15.75,"
                              + "\"kcal\": 180.25"
                              + "}")
            .post("/api/products")
            .then()
                .statusCode(409) //CONFLICT
                .body("mensagem", equalTo("Nome do produto já existe"));
}

    @Test
    public void Deve_Filtrar_Produtos_Por_Criterios() {
        String token = getToken();

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .param("nome", "Bebidas")
                .param("categoria", "Enlatados")
                .param("precoMinimo", 50.0)
                .param("precoMaximo", 200.0)
                .param("marca", "MarcaProduto")
                .when()
                    .get("/api/products")
                    .then()
                    .statusCode(200)
                    .body("nome", equalTo("NomeProduto"))
                    .body("categoria", equalTo("Alimentos"))
                    .body("preco", greaterThanOrEqualTo(50.0))
                    .body("preco", lessThanOrEqualTo(200.0))
                    .body("marca", equalTo("MarcaProduto"));
    }

}
