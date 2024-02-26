package org.maxim.crud;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.maxim.crud.model.Label;
import org.maxim.crud.model.Status;
import org.maxim.crud.repository.LabelRepository;
import org.maxim.crud.repository.hiber.LabelHib;

import org.maxim.crud.service.LabelService;
import org.mockito.Mockito;

import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class LabelTest {
    private static Label correctLabel = null;
    private LabelRepository labelRepository = Mockito.mock(LabelHib.class);
    private LabelService labelServiceUnderTest = new LabelService(labelRepository);

    @BeforeAll
    static void testAll() {
        correctLabel = new Label();
        correctLabel.setId(1);
        correctLabel.setName("Label");
        correctLabel.setStatus(Status.ACTIVE);
    }

    @Test
    void SaveTest() {
        when(labelRepository.save(any())).thenReturn(correctLabel);
        assertEquals(correctLabel, labelServiceUnderTest.save(correctLabel));
    }

    @Test
    void shouldNotSaveNullTest() {
        assertNull(labelServiceUnderTest.save(null));
    }

    @Test
    void yCorrectIdTest() {
        when(labelRepository.getById(1)).thenReturn(correctLabel);
        assertEquals(correctLabel, labelRepository.getById(1));


    }
}
