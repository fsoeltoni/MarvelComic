package com.example.madegeye.marvelcomics.interactors;


import com.example.madegeye.marvelcomics.executors.BackgroundThread;
import com.example.madegeye.marvelcomics.executors.MainThread;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Base interactor test
 */
public class BaseInteractorTest {

    private BaseInteractorTestClass baseInteractorTestClass;

    private TestDisposableObserver<Object> testObserver;

    @Mock BackgroundThread backgroundThread;
    @Mock MainThread mainThread;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        baseInteractorTestClass = new BaseInteractorTestClass(backgroundThread, mainThread);
        testObserver = new TestDisposableObserver<>();

        when(mainThread.getScheduler()).thenReturn(new TestScheduler());
    }

    @Test
    public void testBuildUseCaseObservable_returnsCorrectResult() {
        baseInteractorTestClass.execute(testObserver, Params.EMPTY);

        assertEquals(0, testObserver.valuesCount);
    }

    @Test
    public void testSubscriptionIsProperlyDisposed_whenUseCaseIsDisposed() {
        baseInteractorTestClass.execute(testObserver, Params.EMPTY);
        baseInteractorTestClass.dispose();

        assertTrue(testObserver.isDisposed());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowException_whenPassedNullObserver() {
        baseInteractorTestClass.execute(null, Params.EMPTY);
    }

    private static class BaseInteractorTestClass extends BaseInteractor<Object, Params> {

        BaseInteractorTestClass(BackgroundThread backgroundThread, MainThread mainThread) {
            super(backgroundThread, mainThread);
        }

        @Override
        Observable<Object> buildUseCaseObservable(Params params) {
            return Observable.empty();
        }

        @Override
        public void execute(DisposableObserver<Object> observer, Params p) {
            super.execute(observer, p);
        }
    }

    private static class TestDisposableObserver<T> extends DisposableObserver<T> {
        private int valuesCount  = 0;

        @Override
        public void onNext(T value) {
            valuesCount++;
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }
    }

    private static class Params {
        private static Params EMPTY = new Params();
        private Params() {}
    }
}