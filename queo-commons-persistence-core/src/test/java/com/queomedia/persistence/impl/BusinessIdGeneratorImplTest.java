package com.queomedia.persistence.impl;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.queomedia.persistence.BusinessId;

public class BusinessIdGeneratorImplTest {

    @Test
    public void testGenerateBusinessId() {
        BusinessIdGeneratorImpl classUnderTest = new BusinessIdGeneratorImpl(new UniqueIdGenerator());

        /** call method under test */
        BusinessId<BusinesseEntityTestImpl> bid1 = classUnderTest.generateBusinessId();
        BusinessId<BusinesseEntityTestImpl> bid2 = classUnderTest.generateBusinessId();

        assertNotEquals(bid1, bid2);

    }

}
