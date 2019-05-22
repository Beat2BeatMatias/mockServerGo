import com.google.gson.Gson;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.Delay;
import org.mockserver.model.Header;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class Service {
    static boolean caido=false;
    static MockServerClient mockServer = startClientAndServer(8081);
    static Gson gson;

    public static void consulta(String metodo, String ruta, int codigo,
                                String content, String body, int delay) {
        if(ruta.matches("/sites/levantar")){
            caido=false;
        }
        if(ruta.matches("/sites/romper")){
            caido=true;
        }
        if(caido) {
            mockServer.when(
                    request().withMethod(metodo)
                            .withPath(ruta)
            ).respond(
                    response().withStatusCode(500)
                            .withHeader(new Header("Content-Type", content))
                            .withBody("Error")
                            .withDelay(new Delay(TimeUnit.MILLISECONDS, delay))
            );
        } else {
            mockServer.when(
                    request().withMethod(metodo)
                            .withPath(ruta)
            ).respond(
                    response().withStatusCode(codigo)
                            .withHeader(new Header("Content-Type", content))
                            .withBody(body)
                            .withDelay(new Delay(TimeUnit.MILLISECONDS, delay))
            );
        }
    }

    public static void main(String[] args) {
        gson = new Gson();

        Site site = new Site("MLA","Argentina","AR","not_free",
                3,"ARS","optional");

            consulta("GET", "/sites/[A-Z]{3}", 200, "application/json; charset=utf-8",
                    gson.toJson(site),50);
            consulta("GET", "/sites/levantar", 200, "application/json; charset=utf-8",
                    "Api levantada",50);
            consulta("GET", "/sites/romper", 200, "application/json; charset=utf-8",
                    "Api rota",50);
    }
}
