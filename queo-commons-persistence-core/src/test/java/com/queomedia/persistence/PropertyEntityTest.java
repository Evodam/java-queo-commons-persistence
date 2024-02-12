package com.queomedia.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.StringTokenizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PropertyEntityTest {

    private Entity entity;

    @BeforeEach
    public void setUp() {
        this.entity = new Entity();
    }

    @Test
    public void testGetSetProperty() {
        entity.setProperty("key", "value");

        assertEquals("value", entity.getProperty("key"));
    }

    @Test
    public void testGetPropertyNotSet() {
        assertThrows(RuntimeException.class, () -> entity.getProperty("not existent"));
    }

    @Test
    public void testDoubleSet() {
        assertThrows(RuntimeException.class, () -> {
            entity.setProperty("key", true);
            entity.setProperty("key", false);
        });

    }

    /** Test the conventions method to derive the key name from the class name. */
    @Test
    public void testConventionSetProperty() {
        StringTokenizer value = new StringTokenizer("");

        entity.setProperty(value);
        assertEquals(value, entity.getProperty("stringTokenizer"));
    }

    @Test
    public void testGetProperties() {
        assertTrue(entity.getProperties().isEmpty());

        entity.setProperty("key", "value");
        assertEquals("value", entity.getProperties().get("key"));
    }

    @Test
    public void testGetProperties_unmodifiable() {
        assertThrows(UnsupportedOperationException.class,() ->  entity.getProperties().put("key", "value"));

    }

    @Test
    public void testSetOrOverwriteProperty() {
        entity.setProperty("key", "value");
        entity.setOrOverwriteProperty("key", "value");
    }

    /** Simple class to subclass the property entity abstract class. */
    private static class Entity extends PropertyEntity<Long> {

        private static final long serialVersionUID = -5225043183966676138L;

        public Entity() {
            super(new BusinessId<Long>(1L));
        }
    }

}
