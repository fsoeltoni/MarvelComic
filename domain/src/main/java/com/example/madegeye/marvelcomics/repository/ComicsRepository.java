package com.example.madegeye.marvelcomics.repository;

import com.example.madegeye.marvelcomics.Comic;

import java.util.List;

import io.reactivex.Observable;

/**
 * Repository interface to get comics
 */

public interface ComicsRepository {

    /**
     * Observable that emits list of comics
     */
    Observable<List<Comic>> getComics();

    /**
     * Observale that emits a comic
     *
     * @param comicId
     */
    Observable<Comic> getComicDetails(final int comicId);
}
