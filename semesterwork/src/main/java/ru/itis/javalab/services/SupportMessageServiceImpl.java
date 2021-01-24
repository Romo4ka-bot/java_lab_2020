package ru.itis.javalab.services;

import ru.itis.javalab.models.SupportMessage;
import ru.itis.javalab.repositories.SupportMessageRepository;

public class SupportMessageServiceImpl implements SupportMessageService {
    SupportMessageRepository supportMessageRepository;

    public SupportMessageServiceImpl(SupportMessageRepository supportMessageRepository) {
        this.supportMessageRepository = supportMessageRepository;
    }

    @Override
    public void addSupportMessage(SupportMessage supportMessage) {
        supportMessageRepository.save(supportMessage);
    }
}
