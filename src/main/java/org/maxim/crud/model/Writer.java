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
    @GeneratedValue
    private int id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    /*@JoinTable(name = "posts",
            joinColumns = {
                    @JoinColumn(name = "writer_id")
            }, inverseJoinColumns = {@JoinColumn(name = "post_id")})*/
    private List<Post> posts = new ArrayList<>();

    @Column(name = "writerstatus")
    @Enumerated(EnumType.STRING)
    private Status Status;

    public  Writer(String firstName, String lastName, List<Post> posts, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
        this.Status = status;
    }
    public String toString() {
        return "Writer " + id + ", Firstname " + firstName + ", Lastname " + lastName + " Posts " + posts;
    }


}
