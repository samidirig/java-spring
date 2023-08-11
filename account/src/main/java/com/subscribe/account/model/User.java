package com.subscribe.account.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_gen", initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(name = "name", length = 100)
    String name;
    @Column(name = "surname", length = 100)
    String surname;
    @Column(name = "userName", length = 100)
    String userName;

}
