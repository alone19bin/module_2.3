package org.maxim.RestApi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Event> events;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public User() {

    }

/*public User(String name) {
        this.name = name;
        this.status =Status.ACTIVE;
        this.events =
}*/
}
