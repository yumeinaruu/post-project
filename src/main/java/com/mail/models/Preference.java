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
@Entity(name = "preferences")
public class Preference {
    @Id
    @SequenceGenerator(name = "preferenceSeqGen", sequenceName = "preferences_id_seq_gen", allocationSize = 1)
    @GeneratedValue(generator = "preferenceSeqGen")
    private Long id;

    @Column(name = "other_countries")
    private String otherCountries;

    @Column(name = "within_country")
    private String withinCountry;

    @Column(name = "user_id")
    private Long userId;
}
