package ru.project.carsharing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "originalFullName")
    private String originalFullName;
    @Column(name = "size")
    private Long size;
    @Column(name = "contentType")
    private String contentType;
    @ToString.Exclude
    @Lob                                            //longblob в базе данных
    private byte[] bytes;

}
