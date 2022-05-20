import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ParseoJSON {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        // Creamos la petición utilizando un builder
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://jsonplaceholder.typicode.com/todos/1"))
                .GET()
                .build();

        // Creamos el cliente
        HttpClient client = HttpClient.newHttpClient();

        // Enviamos la petición y obtenemos la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Imprimimos el json por pantalla
        System.out.println(response.body());

        String json = "{\n" +
                "    \"pageInfo\": {\n" +
                "            \"pageName\": \"Homepage\",\n" +
                "            \"logo\": \"https://www.example.com/logo.jpg\"\n" +
                "    },\n" +
                "    \"posts\": [\n" +
                "            {\n" +
                "                \"post_id\": \"0123456789\",\n" +
                "                \"actor_id\": \"1001\",\n" +
                "                \"author_name\": \"Jane Doe\",\n" +
                "                \"post_title\": \"How to parse JSON in Java\",\n" +
                "                \"comments\": [ {\"comment\":\"Muy bueno\"}, {\"comment\":\"Bueno, mas o menos\"} ],\n" +
                "                \"time_of_post\": \"1234567890\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"post_id\": \"AA0\",\n" +
                "                \"actor_id\": \"1002\",\n" +
                "                \"author_name\": \"Jude Law\",\n" +
                "                \"post_title\": \"Titulo del post\",\n" +
                "                \"comments\": [ {\"comment\":\"GENIAL!!!\"}, {\"comment\":\"ERES UN CRACK\"} ],\n" +
                "                \"time_of_post\": \"0987654321\"\n" +
                "            }\n" +
                "    ]\n" +
                "}";

        JSONObject obj = new JSONObject(json);
        String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        System.out.println(pageName);

        JSONArray arr = obj.getJSONArray("posts");
        //JSONArray arrComment = obj.getJSONArray("comments");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("post_id");
            //String comments = arr.getJSONObject(i).getString(arrComment.getJSONObject(i).getString("comment"));
            System.out.println(post_id);
            //System.out.println(comments);
        }

        switch(response.statusCode()){
            case 200:
                procesaRespuesta(response.body());
                break;
            case 404:
                System.out.println("No lo encuentro");
                break;
            case 403:
                System.out.println("La cosa esta chunga");
                break;
        }
    }

    private static void procesaRespuesta(String body) {
        String cadenaJSON = body;
        System.out.println(cadenaJSON);
    }
}
