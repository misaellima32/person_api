package one.digitalinnovation.personapi.repository;

import one.digitalinnovation.personapi.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
