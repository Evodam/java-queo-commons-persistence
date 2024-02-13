package com.queomedia.base.test.hibernate.manytoonelazy;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import com.queomedia.persistence.BusinessEntity;
import com.queomedia.persistence.BusinessId;

@Entity
public class ComponentEntity extends BusinessEntity<ComponentEntity> {

    /**  The Constant serialVersionUID. */
    private static final long serialVersionUID = 7324445876295621456L;
    
    /** Fetchtype layz is required to verify bug RUM-173. */
    @ManyToOne(fetch=FetchType.LAZY)
    private CompositeEntity compositeEntity;
    
    /**
     * Constructor used by Hibernate only.
     * 
     * @deprecated This constructor must be only used by Hibernate.
     * It is not really depreciated, but this marker prevents programmers from using the constructor by mistake.
     */
    @Deprecated
    ComponentEntity() {
        super();
    }
    
    public ComponentEntity(final CompositeEntity compositeEntity, final BusinessId<ComponentEntity> bid) {        
       super(bid);
       this.compositeEntity = compositeEntity;
    }
    
    
}
