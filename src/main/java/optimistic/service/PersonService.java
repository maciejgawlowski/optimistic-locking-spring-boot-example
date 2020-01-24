package optimistic.service;

import lombok.*;
import lombok.extern.slf4j.*;
import optimistic.domain.*;
import optimistic.dto.*;
import optimistic.exception.*;
import optimistic.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public List<Person> getAll() {
        return repository.findAll();
    }

    public Person get(Long id) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent())
            return person.get();
        throw new NoSuchElementException("Person with id #" + id + " does not exist");
    }

    @Transactional
    public Person create(PersonDTO dto) {
        log.info("Creating new person {}", dto.toString());
        Person person = repository.save(new Person(dto.getName(), dto.getSurname(), dto.getCity()));
        log.info("Person created {}", person.toString());
        return person;
    }

    @Transactional
    public Person update(Long id, PersonDTO dto, String eTag) {
        log.info("Updating person with id #{}. New content: {}", id, dto.toString());
        Person person = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person with id #" + id + " does not exist"));
        if (person.hasVersion(eTag)) {
            person = new Person(dto.getId(), dto.getName(), dto.getSurname(), dto.getCity(), dto.getVersion());
            person = repository.save(person);
            log.info("Person updated {}", person.toString());
            return person;
        } else
            throw new EntityVersionException(eTag, person);
    }

    @Transactional
    public void delete(Long id, String eTag) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person with id #" + id + " does not exist"));
        if (person.hasVersion(eTag)) {
            repository.deleteById(id);
            log.info("Person deleted with id #{} deleted", id);
        } else
            throw new EntityVersionException(eTag, person);
    }
}
