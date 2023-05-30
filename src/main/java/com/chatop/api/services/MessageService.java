package com.chatop.api.services;

import com.chatop.api.dto.MessageResponseDTO;
import com.chatop.api.models.MessageEntity;
import com.chatop.api.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public MessageResponseDTO createMessage(MessageEntity message){

        MessageEntity messageCreated = messageRepository.save(message);

        return new MessageResponseDTO(messageCreated.getMessage());
    }
}
