package com.ryan.opensearch_demo.service;

/**
 * @author ryan
 */
public interface SearchService {

    /**
     * search by index
     * @param query keyword in search params
     * @return search result
     */
    String searchByIndex(String query);
}
