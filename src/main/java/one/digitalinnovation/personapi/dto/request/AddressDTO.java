package one.digitalinnovation.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String streetName;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String district;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String addressNumber;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String city;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String state;
}
