package com.artere.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "CONFIG_CACHE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CacheConfig {
    @Id
    private String id;

    private Integer value;
}
