package com.tutorial.springtutorial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {
    @Id
    @SequenceGenerator(name ="user_seq_gen", sequenceName ="user_gen", initialValue =100, allocationSize =1 )
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "surname", length = 100)
    private String surname;
}
