package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Builder(setterPrefix = "with", toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "{user.name.notempty}")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "{user.phone.notempty}")
    @Column(name = "phone")
    private String phone;

    @NotEmpty(message = "{user.address.notempty}")
    @Column(name = "address")
    private String address;


    @NotEmpty(message = "{user.login.notempty}")
    @Column(name = "login")
    private String login;

    @NotEmpty(message = "{user.password.notempty}")
    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;


//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
//    private Collection<OrderDetail> orderDetails;


}
