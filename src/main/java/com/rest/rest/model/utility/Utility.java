package com.rest.rest.model.utility;


import com.rest.rest.model.user.Users;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="utility")
@Data
public class Utility {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name="utility_id")
    private int utilityId;

    @Column(name="utility_name")
    private String utilityName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "utility_provider",
            joinColumns = {@JoinColumn(name = "utility_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<Users> utilityProviders;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "utility_consumer",
            joinColumns = {@JoinColumn(name = "utility_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<Users> utilityConsumers;


}
