package com.mail.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "letters")
public class Letter {
    @Id
    @SequenceGenerator(name = "letterSeqGen", sequenceName = "letters_id_seq_gen", allocationSize = 1)
    @GeneratedValue
    private Long id;

    @Column(name = "header", nullable = false)
    private String header;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "stamp")
    private Byte stamp;

    @Column(name = "letter_type")
    private Byte letterType;

    @Column(name = "sending_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp sendingTime;

    @Column(name = "receiving_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp receivingTime;

    @JoinColumn(name = "sender_user_id")
    @ManyToOne
    private User senderUserId;

    @JoinColumn(name = "receiver_user_id")
    @ManyToOne
    private User receiverUserId;
}
