package org.maxim.RestApi;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.maxim.RestApi.model.Label;
import org.maxim.RestApi.model.Post;
import org.maxim.RestApi.model.Status;
import org.maxim.RestApi.repository.EventRepository;
import org.maxim.RestApi.repository.hiber.EventHib;
import org.maxim.RestApi.service.EventService;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class PostTest {

    private static Post correctPost = null;
    EventRepository postRepository = Mockito.mock(EventHib.class);
    EventService postServiceUnderTest = new EventService(postRepository);

    @BeforeAll
    static void testAll() {
        correctPost = new Post();
        correctPost.setId(1);
        correctPost.setContent("Content");
        correctPost.setStatus(Status.ACTIVE);
    }

    @Test
     public  void saveTest() {
        when(postRepository.save(any())).thenReturn(correctPost);
        assertEquals(correctPost, postServiceUnderTest.save(correctPost));
    }

    @Test
    public void CorrectIdTest() {
        when(postRepository.getById(1)).thenReturn(correctPost);
        assertEquals(correctPost, postRepository.getById(1));
    }
    }
