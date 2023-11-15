package be.ehb.petstore.service;

import be.ehb.petstore.model.ContactMessage;
import be.ehb.petstore.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactMessageRepository contactMessageRepository;

    @Autowired
    public ContactService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    public void saveContactMessage(ContactMessage contactMessage) {
        contactMessageRepository.save(contactMessage);
    }
}
