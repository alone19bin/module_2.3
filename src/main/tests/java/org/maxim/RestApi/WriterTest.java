package org.maxim.RestApi;


import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.maxim.RestApi.model.Status;
import org.maxim.RestApi.model.Writer;
import org.maxim.RestApi.repository.UserRepository;
import org.maxim.RestApi.repository.hiber.UserHib;
import org.maxim.RestApi.service.UserService;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class WriterTest {
    private static Writer correctWriter = null;
    UserRepository writerRepository = Mockito.mock(UserHib.class);
    UserService writerServiceUnderTest = new UserService(writerRepository);

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
