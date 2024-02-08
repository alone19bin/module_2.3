package org.maxim.crud.model;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "labels")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status Status;

    public Label(String name, Status Status) {
        this.name = name;
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Label " + id + ", Name: " + name;
    }
}
