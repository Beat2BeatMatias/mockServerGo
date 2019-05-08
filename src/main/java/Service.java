import com.google.gson.Gson;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.Delay;
import org.mockserver.model.Header;

import java.util.concurrent.TimeUnit;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class Service {

    static MockServerClient mockServer = startClientAndServer(8081);
    static Gson gson;

    public static void consulta(String metodo, String ruta, int codigo,
                                String content, String body, int delay) {
        mockServer.when(
                request().withMethod(metodo)
                        .withPath(ruta)
        ).respond(
                response().withStatusCode(codigo)
                        .withHeader(new Header("Content-Type",content))
                        .withBody(body)
                        .withDelay(new Delay(TimeUnit.MILLISECONDS,delay))
        );
    }

    public static void main(String[] args) {
        gson = new Gson();

        Category[] category = new Category[]{new Category("MLA1234","Accesorios"),
                new Category("MLB1234","Automovil")};
        Site site = new Site("MLA","Argentina","AR","not_free",
                3,"ARS","optional");
        User user = new User(12345678,"MOCKSERVER","hoy","Arg",
                "Nivel Dios", new String[]{"Genio","Capo","Ingeniero"},"MLA");

        consulta("GET", "/sites/[A-Z]{3}", 200, "application/json; charset=utf-8",
                gson.toJson(site),50);
        consulta("GET", "/sites/.*/categories", 200, "application/json; charset=utf-8",
                gson.toJson(category),50);
        consulta("GET", "/users/.*", 200, "application/json; charset=utf-8",
                gson.toJson(user),50);
    }
}
