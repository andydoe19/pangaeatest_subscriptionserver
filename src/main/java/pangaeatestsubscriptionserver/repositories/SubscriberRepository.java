package pangaeatestsubscriptionserver.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import pangaeatestsubscriptionserver.domain.Subscriber;

import java.util.List;

@Repository
public interface SubscriberRepository extends CrudRepository<Subscriber, Long> {

    List<Subscriber> findByTopic(String topic);
}
