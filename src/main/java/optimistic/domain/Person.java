package optimistic.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Getter
@Setter(value = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name = "person_generator", sequenceName = "person_sequence", allocationSize = 1)
    @Setter(value = AccessLevel.PUBLIC)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String city;

    @Version
    @Column(nullable = false)
    private Long version;

    public Person(String name, String surname, String city) {
        this.name = name;
        this.surname = surname;
        this.city = city;
    }

    public boolean hasVersion(String version) {
        return this.getVersion().toString().equals(version);
    }
}
