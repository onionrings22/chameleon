package db.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Category {

    @Id
    private String name;

    @Column
    private boolean active;

    @Column
    private boolean last;

    @Column
    private Integer num;
}


