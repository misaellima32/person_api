package one.digitalinnovation.personapi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.exception.PhoneNotFoundException;
import one.digitalinnovation.personapi.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/phone")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PhoneController {

    private PhoneService phoneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPhone(@RequestBody PhoneDTO phoneDTO) {

        return phoneService.createPhone(phoneDTO);
    }

    @GetMapping
    public List<PhoneDTO> listAllPhones() { return phoneService.listAllPhones(); }

    @GetMapping("/{id}")
    public PhoneDTO findPhoneById(@PathVariable Long id) throws PhoneNotFoundException {
        return phoneService.findPhoneById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updatePhoneById(@PathVariable Long id, @RequestBody @Valid PhoneDTO phoneDTO) throws PhoneNotFoundException {
        return phoneService.updatePhoneById(id, phoneDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePoneById(@PathVariable Long id) throws PhoneNotFoundException {
        phoneService.deletePhone(id);
    }
}
