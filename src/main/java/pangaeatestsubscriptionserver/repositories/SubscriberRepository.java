package pangaeatestsubscriptionserver.repositories;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import pangaeatestsubscriptionserver.domain.Subscriber;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriberRepository extends CrudRepository<Subscriber, Long> {

    List<Subscriber> findByTopic(String topic);

    Optional<Subscriber> findByTopicAndSubscriberUrl(String topic, String subscriberUrl);
}
