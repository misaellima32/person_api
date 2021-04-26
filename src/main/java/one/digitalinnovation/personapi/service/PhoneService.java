package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Phone;
import one.digitalinnovation.personapi.exception.PhoneNotFoundException;
import one.digitalinnovation.personapi.mapper.PhoneMapper;
import one.digitalinnovation.personapi.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PhoneService {

    private PhoneRepository phoneRepository;

    private final PhoneMapper phoneMapper = PhoneMapper.INSTANCE;

    public MessageResponseDTO createPhone(PhoneDTO phoneDTO) {
        Phone personToSave = phoneMapper.toModel(phoneDTO);

        Phone savedPerson = phoneRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public List<PhoneDTO> listAllPhones() {
        List<Phone> allPhones = phoneRepository.findAll();
        return allPhones.stream().map(phoneMapper::toDTO).collect(Collectors.toList());
    }

    public PhoneDTO findPhoneById(Long id) throws PhoneNotFoundException {
        Phone person = verifyIfExists(id);

        return phoneMapper.toDTO(person);
    }

    public void deletePhone(Long id) throws PhoneNotFoundException {
        phoneRepository.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException(id));

        phoneRepository.deleteById(id);
    }

    public MessageResponseDTO updatePhoneById(Long id, PhoneDTO phoneDTO) throws PhoneNotFoundException {
        phoneRepository.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException(id));

        Phone updatedPhone = phoneMapper.toModel(phoneDTO);
        Phone savedPhone = phoneRepository.save(updatedPhone);

        MessageResponseDTO messageResponse = createMessageResponse(savedPhone.getId(), "Phone successfully updated with ID ");
        return messageResponse;
    }

    private Phone verifyIfExists(Long id) throws PhoneNotFoundException {
        return phoneRepository
                .findById(id)
                .orElseThrow(() -> new PhoneNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
