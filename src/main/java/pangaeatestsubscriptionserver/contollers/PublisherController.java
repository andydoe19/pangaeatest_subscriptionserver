package pangaeatestsubscriptionserver.contollers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.HttpClient;
import pangaeatestsubscriptionserver.domain.Subscriber;
import pangaeatestsubscriptionserver.repositories.SubscriberRepository;

import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller("/publish")
public class PublisherController {

    private final SubscriberRepository subscriberRepository;

    public PublisherController(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    /************************
     * ENDPOINTS
     ************************/

    @Post("/{topic}")
    public HttpResponse<?> publish(HttpRequest<Map<String, Object>> request,
                                   @NotNull String topic) throws MalformedURLException {

        //retrieve request body
        Optional<Map<String, Object>> bodyOptional = (Optional<Map<String, Object>>) request.getBody();
        System.out.println("about to check request body");
        if (!bodyOptional.isPresent())
            return HttpResponse.badRequest("Request body cannot be empty");

        Map<String, Object> body = bodyOptional.get();
        System.out.println(body.toString());

        //retrieve subscribers of a requested topic
        List<Subscriber> lstSubscribers = subscriberRepository.findByTopic(topic);
        for (Subscriber subscriber : lstSubscribers) {

            //get base url
            String baseUrl = "";
            URL url = new URL(subscriber.getSubscriberUrl());
            if(url.getPort() == -1){ // port is not
                baseUrl = url.getProtocol()+"://"+url.getHost()+"/";
            } else {
                baseUrl = url.getProtocol()+"://"+url.getHost()+":"+url.getPort()+"/";
            }

            //fire request to subscriber server
            HttpClient client = HttpClient.create(new URL(baseUrl));
            HttpResponse<?> response = client.toBlocking()
                    .exchange(
                            HttpRequest.POST(url.getFile(), body)
                    );
        }

        return HttpResponse.ok("Successfully published message");
    }
}
