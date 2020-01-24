package optimistic.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Long id;
    private String name;
    private String surname;
    private String city;
    private Long version;
}
