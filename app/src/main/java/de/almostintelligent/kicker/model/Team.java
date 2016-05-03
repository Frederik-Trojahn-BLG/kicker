package de.almostintelligent.kicker.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity(name = "team")
@EqualsAndHashCode(callSuper = false)
public class Team extends BaseEntity {

    private String name;

    @ManyToMany(targetEntity = Account.class, mappedBy = "teams")
    private Set<Account> members;

    @ManyToMany(targetEntity = League.class, mappedBy = "teams")
    private Set<League> leagues;

}
