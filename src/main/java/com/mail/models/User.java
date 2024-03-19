package com.mail.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@ToString(exclude = {"outcomeLetters", "incomeLetters"})
@EqualsAndHashCode(exclude = {"outcomeLetters", "incomeLetters"})
@Component
public class User {
    @Id
    @SequenceGenerator(name = "userSeqGen", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "userSeqGen")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "user_location", nullable = false)
    private String userLocation;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp registrationDate;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "change_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp changeDate;

    @OneToMany(mappedBy = "receiverUserId", fetch = FetchType.EAGER)
    private Collection<Letter> incomeLetters;

    @OneToMany(mappedBy = "senderUserId", fetch = FetchType.EAGER)
    private Collection<Letter> outcomeLetters;
}
