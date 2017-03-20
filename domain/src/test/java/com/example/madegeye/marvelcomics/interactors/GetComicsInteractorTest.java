package com.example.madegeye.marvelcomics.interactors;

import com.example.madegeye.marvelcomics.executors.BackgroundThread;
import com.example.madegeye.marvelcomics.executors.MainThread;
import com.example.madegeye.marvelcomics.repository.ComicsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * test for Get Comics Interactor
 */
public class GetComicsInteractorTest {

    @Mock ComicsRepository comicsRepository;
    @Mock BackgroundThread backgroundThread;
    @Mock MainThread mainThread;

    private GetComicsInteractor getComicsInteractor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        getComicsInteractor = new GetComicsInteractor(comicsRepository, backgroundThread, mainThread);
    }

    @Test
    public void testGetComicsList_successCase() {
        getComicsInteractor.buildUseCaseObservable(null);

        verify(comicsRepository).getComics();
        verifyNoMoreInteractions(comicsRepository);
        verifyZeroInteractions(mainThread);
        verifyZeroInteractions(backgroundThread);
    }

}