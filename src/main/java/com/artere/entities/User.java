package com.artere.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "USERS")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class User {
    @Id
    private String name;

    private String email;
}
