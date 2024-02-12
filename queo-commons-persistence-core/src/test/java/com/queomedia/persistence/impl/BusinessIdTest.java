package com.queomedia.persistence.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.queomedia.persistence.BusinessId;

public class BusinessIdTest {

    @Test
    public void cloneTest() {
        final long id = 123;
        BusinessId<BusinesseEntityTestImpl> bid = new BusinessId<BusinesseEntityTestImpl>(id);

        /* call method under test */
        BusinessId<BusinesseEntityTestImpl> result1 = bid.clone();

        Assertions.assertEquals(id, result1.getBusinessId());
    }
    
    @Test
    public void BusinessIdStringLengthTest() {                
        BusinessId<BusinesseEntityTestImpl> maxBid = new BusinessId<BusinesseEntityTestImpl>(Long.MAX_VALUE);
        BusinessId<BusinesseEntityTestImpl> minBid = new BusinessId<BusinesseEntityTestImpl>(Long.MIN_VALUE);
        BusinessId<BusinesseEntityTestImpl> zeroBid = new BusinessId<BusinesseEntityTestImpl>(0);
                
        Assertions.assertEquals(BusinessId.BUSINESS_ID_STRING_LENGTH - 1, maxBid.getAsString().length());
        Assertions.assertEquals(BusinessId.BUSINESS_ID_STRING_LENGTH, minBid.getAsString().length());        
        Assertions.assertTrue(BusinessId.BUSINESS_ID_STRING_LENGTH >= zeroBid.getAsString().length());
    }
}
