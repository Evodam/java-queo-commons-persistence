package com.queomedia.persistence.schema.demo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;

@Embeddable
public class DemoEmbeddable {

    @Size(max=100)
    private String embeddedString;
}
