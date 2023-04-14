package com.chatop.api.repositories;


import com.chatop.api.models.RentalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<RentalEntity,Long> {
}
