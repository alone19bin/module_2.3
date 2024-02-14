package org.maxim.crud;


import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.maxim.crud.model.Status;
import org.maxim.crud.model.Writer;
import org.maxim.crud.repository.WriterRepository;
import org.maxim.crud.repository.hiber.WriterHib;
import org.maxim.crud.service.WriterService;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class WriterTest {
    private static Writer correctWriter = null;
    WriterRepository writerRepository = Mockito.mock(WriterHib.class);
    WriterService writerServiceUnderTest = new WriterService(writerRepository);

    @BeforeAll
    static void testAll() {
        correctWriter = new Writer();
        correctWriter.setId(1);
        correctWriter.setLastName("Ivan");
        correctWriter.setFirstName("Ivanov");
        correctWriter.setStatus(Status.ACTIVE);
    }

    @Test
    public void SaveTest() {
        when(writerRepository.save(any())).thenReturn(correctWriter);
        assertEquals(correctWriter, writerServiceUnderTest.save(correctWriter));
    }

    @Test
    public void CorrectIdTest() {
        when(writerRepository.getById(1)).thenReturn(correctWriter);
        assertEquals(correctWriter, writerRepository.getById(1));
    }
}
