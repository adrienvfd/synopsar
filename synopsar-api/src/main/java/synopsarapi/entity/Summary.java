package synopsarapi.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Summary {

    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;
    String userId;
    String url;
    String title;
    String text;
    @CreationTimestamp
    @Column(name = "date")
    Instant date;
}
