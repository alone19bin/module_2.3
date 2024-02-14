package org.maxim.crud;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.maxim.crud.model.Label;
import org.maxim.crud.model.Post;
import org.maxim.crud.model.Status;
import org.maxim.crud.repository.PostRepository;
import org.maxim.crud.repository.hiber.PostHib;
import org.maxim.crud.service.PostService;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


public class PostTest {

    private static Post correctPost = null;
    PostRepository postRepository = Mockito.mock(PostHib.class);
    PostService postServiceUnderTest = new PostService(postRepository);

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
