package com.example.madegeye.marvelcomics.repository.datastore;

import com.example.madegeye.marvelcomics.entity.ComicsEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * comics local data store
 */

public class ComicsLocalDataStore implements ComicsDataStore {
    @Override
    public Observable<List<ComicsEntity>> getComicsEntityList() {
        return null;
    }

    @Override
    public Observable<ComicsEntity> getComicEntityDetails(int comicId) {
        return null;
    }
}
