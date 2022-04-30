package com.example.onlineshoppingdemo.ds;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false,unique = true)
    @NotEmpty
    @Email(message = "{Errors.in.invalid}")
    private String email;
    @NotEmpty
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },inverseJoinColumns = {
            @JoinColumn(name = "role_id")
    }
    )
    private Set<Role> roles;

}
