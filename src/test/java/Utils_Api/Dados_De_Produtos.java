package Utils_Api;

public class Dados_De_Produtos {

    public static String getProductRequestBody() {
        return "{"
                + "\"nome\": \"Produto Teste\","
                + "\"foto\": \"base64_encoded_image_string\","
                + "\"categoria\": \"Eletrônicos\","
                + "\"unidade\": \"Unidade\","
                + "\"toggleStatus\": true,"
                + "\"valorEmCreditos\": 100,"
                + "\"valorDescontoSalarial\": 10.50,"
                + "\"kcal\": 150.75"
                + "}";
    }
}