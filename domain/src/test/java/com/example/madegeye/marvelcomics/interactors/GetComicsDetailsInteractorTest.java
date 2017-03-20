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
 * Get comics details interactor test
 */
public class GetComicsDetailsInteractorTest {

    @Mock ComicsRepository comicsRepository;
    @Mock MainThread mainThread;
    @Mock BackgroundThread backgroundThread;

    private GetComicsDetailsInteractor getComicsDetailsInteractor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        getComicsDetailsInteractor = new GetComicsDetailsInteractor(comicsRepository,
                backgroundThread, mainThread);
    }

    @Test
    public void shouldGetComicsDetailsSuccessCase() {
        getComicsDetailsInteractor.buildUseCaseObservable(GetComicsDetailsInteractor.Params.forComic(10));

        verify(comicsRepository).getComicDetails(10);
        verifyNoMoreInteractions(comicsRepository);
        verifyZeroInteractions(mainThread);
        verifyZeroInteractions(backgroundThread);
    }

    @Test(expected = NullPointerException.class)
    public void shouldFail_whenNullOrEmptyParameterIsPassed() {
        getComicsDetailsInteractor.buildUseCaseObservable(null);
    }
}