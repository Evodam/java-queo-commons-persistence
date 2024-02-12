package com.queomedia.persistence.util;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.queomedia.commons.exceptions.NotFoundRuntimeException;

public class ResultUtilTest {

    @Test
    public void testRequiredOneOrNoResult() {

        Object entity = new Object();
        List<Object> oneElementCollection = Arrays.asList(entity);

        Object result = ResultUtil.requiredOneOrNoResult(oneElementCollection);

        assertSame(entity, result);
    }

    @Test
    public void testRequiredOneOrNoResultEmpty() {

        List<Object> emptyElementCollection = Arrays.asList();

        Object result = ResultUtil.requiredOneOrNoResult(emptyElementCollection);

        assertNull(result);
    }

    @Test
    public void testRequiredOneOrNoResultToMuch() {

        List<Object> emptyElementCollection = Arrays.asList(new Object(), new Object());
        assertThrows(IncorrectResultSizeDataAccessException.class,
                () -> ResultUtil.requiredOneOrNoResult(emptyElementCollection));
    }

    @Test
    public void testRequiredOneResult() {

        Object entity = new Object();
        List<Object> oneElementCollection = Arrays.asList(entity);

        Object result = ResultUtil.requiredOneResult(oneElementCollection, "");

        assertSame(entity, result);
    }

    @Test
    public void testRequiredOneResultEmpty() {

        List<Object> emptyElementCollection = Arrays.asList();

        assertThrows(NotFoundRuntimeException.class,
                () -> ResultUtil.requiredOneResult(emptyElementCollection, ""));
    }

    @Test
    public void testRequiredOneResultToMuch() {

        List<Object> emptyElementCollection = Arrays.asList(new Object(), new Object());
        assertThrows(IncorrectResultSizeDataAccessException.class,
                () -> ResultUtil.requiredOneResult(emptyElementCollection, ""));
    }

}
