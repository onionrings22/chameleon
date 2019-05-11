package db.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Item {

    @Id
    private Integer id;

    @Column
    private String category;

    @Column
    private String text;
}


