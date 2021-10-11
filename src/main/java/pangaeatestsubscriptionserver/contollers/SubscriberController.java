package pangaeatestsubscriptionserver.contollers;

import io.micronaut.http.annotation.Get;
import pangaeatestsubscriptionserver.DTOs.CreateSubscriptionDTO;
import pangaeatestsubscriptionserver.DTOs.SubscribeResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import pangaeatestsubscriptionserver.domain.Subscriber;
import pangaeatestsubscriptionserver.repositories.SubscriberRepository;

import javax.validation.Valid;

@Controller("/subscribe")
public class SubscriberController {

    private final SubscriberRepository subscriberRepository;

    public SubscriberController(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    /************************
     * ENDPOINTS
     ************************/

    @Post("/{topic}")
    public SubscribeResponse create(@Body @Valid CreateSubscriptionDTO subscription,
                                    String topic){
        //save subscriber to db
        subscriberRepository.save(Subscriber.builder()
                .topic(topic)
                .subscriberUrl(subscription.getUrl())
                .build());

        //output subscription success response
        return SubscribeResponse.builder()
                .url(subscription.getUrl())
                .topic(topic)
                .build();
    }

    @Get("/list")
    public Iterable<Subscriber> listWallets() {
        return subscriberRepository.findAll();
    }

}