package com.chatop.api.token;

import com.chatop.api.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Column(unique = true)
    public String token;

    public boolean revoked;

    public boolean expired;

}
