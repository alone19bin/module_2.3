package org.maxim.crud.model;

import jakarta.persistence.*;
import lombok.*;

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
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "created")
    private String created;

    @Column(name = "updated")
    private String updated;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "post_labels",
            joinColumns = {
                    @JoinColumn(name = "post_id")
            }, inverseJoinColumns = {@JoinColumn(name = "label_id")})
    private List<Label> labels;

    @Column(name = "post_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Writer writer;

    public Post(String created, String updated, Status postStatus, List<Label> labels, Writer writer) {
        this.created = created;
        this.updated = updated;
        this.status = postStatus;
        this.labels = labels;
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Post " + id + ", Content: " + content + ", Created: " + created + ", Updated: " + updated + ", Labels: " + labels;
    }
}
