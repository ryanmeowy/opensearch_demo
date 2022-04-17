package com.ryan.opensearch_demo.controller;

import com.ryan.opensearch_demo.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ryan
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource
    private SearchService searchService;

    @GetMapping("/by-index")
    String searchByIndex(@RequestParam("index") String key) {
        String indexPrefix = "default:'%s'";
        String query = String.format(indexPrefix,key);
        return searchService.searchByIndex(query);
    }


}
