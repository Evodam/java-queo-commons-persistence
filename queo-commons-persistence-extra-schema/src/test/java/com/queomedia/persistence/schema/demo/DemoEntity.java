package com.queomedia.persistence.schema.demo;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@Entity
public class DemoEntity {

    @Id
    private int id;

    @Size(max = 100)
    private String normalString;

    /**
     * To get the JSR 303 Annotations working on embeddeds for Schema generation,
     * @Valid must beend added: https://hibernate.atlassian.net/browse/ANN-652
     */
    @Embedded
    @Valid
    private DemoEmbeddable demoEmbeddable;
}
