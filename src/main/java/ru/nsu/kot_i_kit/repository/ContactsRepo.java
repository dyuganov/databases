package ru.nsu.kot_i_kit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nsu.kot_i_kit.entity.Contact;

public interface ContactsRepo extends JpaRepository<Contact, Long> {

    Contact findContactByTelegramLink(String link);



}
