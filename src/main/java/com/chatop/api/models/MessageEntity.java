package com.chatop.api.models;

import jakarta.persistence.*;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String message;
        @ManyToOne
        @JoinColumn(name = "user_id")
        private UserEntity user;
        @ManyToOne
        @JoinColumn(name = "rental_id")
        private RentalEntity rental;
        @Column(name = "created_at")
        private LocalDate createdAt;
        @Column(name = "updated_at")
        private LocalDate updatedAt;

}
