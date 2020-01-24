package optimistic.repository;

import optimistic.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
