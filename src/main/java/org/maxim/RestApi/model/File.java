package org.maxim.RestApi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public File(Integer id, String name, String filePath) {
        this.id =id;
        this.name = name;
        this.filePath = filePath;
        this.status = Status.ACTIVE;
    }
    public File(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
        this.status = Status.ACTIVE;
    }
    @Override
    public String toString() {
        return "{" + "\"id\":" + id +
                ", \"fileName\":" + "\"" + filePath + "\"" +
                ", \"filePath\":" + "\"" + filePath + "\"" +
                "}";
    }
}
