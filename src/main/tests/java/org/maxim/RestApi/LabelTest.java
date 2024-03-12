package org.maxim.RestApi;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.maxim.RestApi.model.Label;
import org.maxim.RestApi.model.Status;
import org.maxim.RestApi.repository.FileRepository;
import org.maxim.RestApi.repository.hiber.FileHib;

import org.maxim.RestApi.service.FileService;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class LabelTest {
    private static Label correctLabel = null;
    private FileRepository labelRepository = Mockito.mock(FileHib.class);
    private FileService labelServiceUnderTest = new FileService(labelRepository);

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
