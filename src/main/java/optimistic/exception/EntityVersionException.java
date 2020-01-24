package optimistic.exception;

import lombok.extern.slf4j.*;
import optimistic.domain.*;

@Slf4j
public class EntityVersionException extends RuntimeException {

    public EntityVersionException(String eTag, Person person) {
        super(String.format("Entity with id #%d has version %d, request header contains eTag %s", person.getId(), person.getVersion(), eTag));
        log.info(this.getMessage());
    }
}
