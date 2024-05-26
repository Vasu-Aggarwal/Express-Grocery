package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;

    private String refreshToken;
    private Instant expiry;

    @OneToOne
    @JoinColumn(name = "user_uuid")
    private User user;

}
