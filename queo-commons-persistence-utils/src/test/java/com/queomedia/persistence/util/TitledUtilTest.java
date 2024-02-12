package com.queomedia.persistence.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.queomedia.persistence.domain.Titled;

public class TitledUtilTest {

    @Test
    public void testTryCastToTitled_Titled() throws Exception {
        
        assertTrue(TitledUtil.tryCastToTitled(new CustomTilted()).isPresent());
    }
    
    @Test
    public void testTryCastToTitled_NotTitled() throws Exception {
        
        assertFalse(TitledUtil.tryCastToTitled(new CustomNotTilted()).isPresent());
    }

    @Test
    public void testExtractEntityTitle_Titled() throws Exception {
        
        assertEquals("CustomTilted", TitledUtil.extractEntityTitle(new CustomTilted()).get());
    }
    
    @Test
    public void testExtractEntityTitle_NotTitled() throws Exception {
        
        assertFalse(TitledUtil.extractEntityTitle(new CustomNotTilted()).isPresent(), "CustomTilted");
    }
    
    public static class CustomTilted implements Titled {

        @Override
        public String getTitle() {
            return "CustomTilted";
        }
        
    }
    
    public static class CustomNotTilted  {
        
    }
    
    

}
