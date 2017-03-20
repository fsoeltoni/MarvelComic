package com.example.madegeye.marvelcomics.interactors;

import com.example.madegeye.marvelcomics.Comic;
import com.example.madegeye.marvelcomics.executors.BackgroundThread;
import com.example.madegeye.marvelcomics.executors.MainThread;
import com.example.madegeye.marvelcomics.repository.ComicsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Interactor to get Comics
 */


public class GetComicsInteractor extends BaseInteractor<List<Comic>, Void> {

    private ComicsRepository comicsRepository;


    @Inject
    public GetComicsInteractor(ComicsRepository repository,
                               BackgroundThread backgroundThread,
                               MainThread mainThread) {

        super(backgroundThread, mainThread);
        this.comicsRepository = repository;
    }

    @Override
    Observable<List<Comic>> buildUseCaseObservable(Void aVoid) {
        return comicsRepository.getComics();
    }
}
