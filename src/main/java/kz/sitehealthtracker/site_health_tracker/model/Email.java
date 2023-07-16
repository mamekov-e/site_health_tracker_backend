package kz.sitehealthtracker.site_health_tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "emails")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Email extends BaseEntity<Long> {
    @Column(name = "address")
    private String address;
    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "enabled")
    private boolean enabled = false;
}
