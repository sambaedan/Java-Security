package com.rest.rest.model.user;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Data
@Table(name="roles")
@Entity
public class Role  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column( name = "role_id")
    private Long id;

    @Column( name = "role_name" )
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    Set<Users>  user;
}
