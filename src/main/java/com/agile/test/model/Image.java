package com.agile.test.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    private String id;
    private String link;
    private Integer page;
}
