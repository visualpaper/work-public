import javax.annotation.Nonnull
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.Response

class MyHttp {
    String url;

    @Nonnull
    Response request() {
        assert url != ""

        return ClientBuilder.newClient()
                .target(url)
                .request()
                .get();
    }
}
