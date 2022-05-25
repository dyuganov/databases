package ru.nsu.kot_i_kit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.kot_i_kit.model.Contact;

public interface ContactsRepo extends JpaRepository<Contact, Long> {
    Contact findContactByTelegramLink(String link);

}
