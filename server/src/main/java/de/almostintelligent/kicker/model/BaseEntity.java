package de.almostintelligent.kicker.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
abstract public class BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String id;

    @Column(name = "created_at")
    protected Long createdAt;

    @Column(name = "updated_at")
    protected Long updatedAt;

    @Column(name = "deleted", columnDefinition = "TINYINT(1) DEFAULT 0")
    protected Boolean deleted;

    @PrePersist
    public void prePersist() {
        createdAt = new Date().getTime();
        updatedAt = new Date().getTime();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date().getTime();
    }
}
