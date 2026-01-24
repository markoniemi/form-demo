package com.example.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field label is required")
    @Size(max = 255, message = "Field label must be at most 255 characters")
    private String label;

    @NotBlank(message = "Field type is required")
    private String type; // e.g., text, number, date, choice
    private boolean required;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private Form form;
}
