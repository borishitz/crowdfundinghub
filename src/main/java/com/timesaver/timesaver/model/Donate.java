package com.timesaver.timesaver.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Donate  extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Donation donation;

    private Long amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id")
    private Admin admin;
}
