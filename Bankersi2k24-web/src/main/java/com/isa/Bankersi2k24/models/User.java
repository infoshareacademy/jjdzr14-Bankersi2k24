package com.isa.Bankersi2k24.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue
    private BigInteger id;
    private String name ;
    @NotBlank(message = "Please provide a login")
    private String login;
    @NotBlank(message = "Please provide a password")
    private String password;
    private String lastName;
    @Email(message = "Provide valid email format")
//    @NotBlank(message = "email cannot be empty")
//    @NotNull(message = "email cannot be empty")
    private String email;
    private String taxId;
    private Date creationDate;
    @OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<BankAccount> bankAccounts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) && Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getCreationDate(), user.getCreationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLogin(), getEmail(), getCreationDate());
    }
}
