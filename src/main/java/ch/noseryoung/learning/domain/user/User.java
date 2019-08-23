package ch.noseryoung.learning.domain.user;

import ch.noseryoung.learning.domain.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    @OneToMany(mappedBy = "user")
    private Collection<Order> orders;

}
