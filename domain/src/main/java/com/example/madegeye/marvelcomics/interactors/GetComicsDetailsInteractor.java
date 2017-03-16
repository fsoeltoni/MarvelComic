package com.example.madegeye.marvelcomics.interactors;

import com.example.madegeye.marvelcomics.Comic;
import com.example.madegeye.marvelcomics.executors.BackgroundThread;
import com.example.madegeye.marvelcomics.executors.MainThread;
import com.example.madegeye.marvelcomics.repository.ComicsRepository;
import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Get comic details interactor
 */

public class GetComicsDetailsInteractor extends
        BaseInteractor<Comic, GetComicsDetailsInteractor.Params> {

    private final ComicsRepository comicsRepository;

    @Inject
    public GetComicsDetailsInteractor(ComicsRepository comicsRepository,
                                      BackgroundThread backgroundThread, MainThread mainThread) {
        super(backgroundThread, mainThread);
        this.comicsRepository = comicsRepository;
    }

    @Override
    Observable<Comic> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return  comicsRepository.getComicDetails(params.comicId);

    }

    public static final class Params {
        private int comicId;

        private Params(int id) {
            this.comicId = id;
        }

        public static Params forComic(int id) {
            return new Params(id);
        }
    }
}
