package com.example.madegeye.marvelcomics.repository.datastore;

import com.example.madegeye.marvelcomics.entity.ComicsEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Comic data store interface for the data layer
 */

public interface ComicsDataStore {

    /**
     * get comic entity list
     *
     * @return
     */
    Observable<List<ComicsEntity>> getComicsEntityList();

    /**
     * get comic details
     *
     * @param comicId
     * @return
     */
    Observable<ComicsEntity> getComicEntityDetails(final int comicId);
}
