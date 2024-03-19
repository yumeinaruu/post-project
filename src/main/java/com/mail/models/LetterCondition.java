package com.mail.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "letter_condition")
public class LetterCondition {
    @Id
    @SequenceGenerator(name = "LetterCondSeqGen", sequenceName = "letter_condition_id_seq_gen", allocationSize = 1)
    @GeneratedValue(generator = "LetterCondSeqGen")
    private Long id;

    @Column(name = "condition", nullable = false)
    private String condition;

    @Column(name = "letter_id", nullable = false)
    private Long letterId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}

