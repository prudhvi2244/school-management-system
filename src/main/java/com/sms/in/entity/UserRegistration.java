package com.sms.in.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    @Column
    @NotEmpty(message = "first name is required")
    private String fname;
    @Column
    @NotEmpty(message = "last name is required")
    private String lname;
    @Column
    @NotEmpty(message = "username is required")
    private String username;
    @Column
    @NotEmpty(message = "password is required")
    private String password;
    @Column
    @NotEmpty(message = "email is required")
    @Email(message = "valid email address is required")
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private EGender Gender;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",joinColumns = @JoinColumn(name = "uid"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<ERole> roles;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;
}
