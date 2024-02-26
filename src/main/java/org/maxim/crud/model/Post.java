package org.maxim.crud.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "created")
    @CreationTimestamp
    private String created;

    @Column(name = "updated")
    @CreationTimestamp
    private String updated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "post_labels",
            joinColumns = {
                    @JoinColumn(name = "post_id")
            }, inverseJoinColumns = {@JoinColumn(name = "label_id")})
    private List<Label> labels;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Writer writer;

    @Override
    public String toString() {
        return "Post " + id + ", Content: " + content + ", Created: " + created + ", Updated: " + updated + ", Labels: " + labels;
    }


}
