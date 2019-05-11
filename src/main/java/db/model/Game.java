package db.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Game {

    @Id
    private Integer id;

    @Column
    private boolean start;

    @Column(name = "voting_done")
    private boolean votingDone;

    @Column
    private boolean reveal;

    @Column
    private boolean done;
}


