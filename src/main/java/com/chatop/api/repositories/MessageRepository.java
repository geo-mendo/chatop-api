package com.chatop.api.repositories;

import com.chatop.api.models.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
}
