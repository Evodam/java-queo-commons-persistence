package com.queomedia.persistence.schema.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class DemoEntityWithMinConstraint {

    @Id
    private int id;
    
    @Min(1)
    private int minValue;
    
    @Max(3)
    private int maxValue;
    
}
