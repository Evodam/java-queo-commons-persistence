package com.queomedia.persistence.schema.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DemoEntiyWithRelation {

    @Id
    private long id;

    @ManyToOne
    private DemoEntity demoEntity;
}
