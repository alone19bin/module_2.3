package org.maxim.crud.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "writers")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<Post> posts;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public  Writer(String firstName, String lastName, List<Post> posts, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
        this.status = status;
    }
    public String toString() {
        return "Writer " + id + ", Firstname " + firstName + ", Lastname " + lastName + " Posts " + posts;
    }


}
