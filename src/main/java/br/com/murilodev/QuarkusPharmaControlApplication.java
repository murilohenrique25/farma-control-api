package br.com.murilodev;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "API Controle de entregas",
                version = "1.0",
                contact = @Contact(
                        name = "Murilo Henrique",
                        email = "murilohenrique.dev@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "htpps://www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)
public class QuarkusPharmaControlApplication extends Application {
}
