package com.example.madegeye.marvelcomics.interactors;

import com.example.madegeye.marvelcomics.executors.BackgroundThread;
import com.example.madegeye.marvelcomics.executors.MainThread;
import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Base interactor class
 */

public abstract class BaseInteractor<T, Params> {

    private final BackgroundThread backgroundThreadExecutor;
    private final MainThread mainThreadExecutor;
    private final CompositeDisposable disposables;

    public BaseInteractor(BackgroundThread backgroundThread, MainThread mainThread) {
        this.backgroundThreadExecutor = backgroundThread;
        this.mainThreadExecutor = mainThread;
        this.disposables = new CompositeDisposable();
    }

    /**
     * returns the observable implementation for this use case
     *
     * @param params
     * @return
     */
    abstract Observable<T> buildUseCaseObservable(Params params);

    /**
     * executes the use case
     *
     * @param p
     * @param observer
     */
    public void execute(DisposableObserver<T> observer, Params p) {
        Preconditions.checkNotNull(observer);

        final Observable<T> observable = this.buildUseCaseObservable(p)
                .subscribeOn(Schedulers.from(backgroundThreadExecutor))
                .observeOn(mainThreadExecutor.getScheduler());

        addDisposable(observable.subscribeWith(observer));

    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }


    private void addDisposable(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);

        disposables.add(disposable);
    }
}
