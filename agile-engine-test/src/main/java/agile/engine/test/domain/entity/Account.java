package agile.engine.test.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "account_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "name")
    private String name;

}
