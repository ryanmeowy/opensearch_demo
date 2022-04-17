package com.ryan.opensearch_demo.service.impl;

import com.aliyun.opensearch.OpenSearchClient;
import com.aliyun.opensearch.SearcherClient;
import com.aliyun.opensearch.sdk.generated.OpenSearch;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchClientException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchException;
import com.aliyun.opensearch.sdk.generated.search.*;
import com.aliyun.opensearch.sdk.generated.search.general.SearchResult;
import com.google.common.collect.Lists;
import com.ryan.opensearch_demo.config.OpensearchProperties;
import com.ryan.opensearch_demo.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ryan
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private OpensearchProperties properties;

    @Override
    public String searchByIndex(String query) {
        OpenSearch openSearch = new OpenSearch(properties.getAccessKey(), properties.getSecret(), properties.getHost());
        OpenSearchClient serviceClient = new OpenSearchClient(openSearch);
        SearcherClient searcherClient = new SearcherClient(serviceClient);
        //Config对象，用于设定config子句参数，指定应用名，分页，数据返回格式等等
        Config config = new Config(Lists.newArrayList(properties.getAppName()));
        config.setStart(0);
        config.setHits(10);
        //设置返回格式为json格式
        config.setSearchFormat(SearchFormat.JSON);
        // 创建参数对象
        SearchParams searchParams = new SearchParams(config);
        /*指定搜索的关键词，这里要指定在哪个索引上搜索，如果不指定的话默认在使用“default”索引，
        若需多个索引组合查询，需要在setQuery处合并，否则若设置多个setQuery子句，则后面的子句会替换前面子句*/
        searchParams.setQuery(query);
        // 设置查询过滤条件
        searchParams.setFilter("sm_id > 0");
        //创建sort对象，并设置二维排序
        Sort sort = new Sort();
        //设置id字段降序
        sort.addToSortFields(new SortField("sm_id", Order.DECREASE));
        //若id相同则以RANK相关性算分升序
        sort.addToSortFields(new SortField("RANK", Order.INCREASE));
        //添加Sort对象参数
        searchParams.setSort(sort);
        try {
            SearchResult searchResult = searcherClient.execute(searchParams);
            return searchResult.getResult();
        } catch (OpenSearchException | OpenSearchClientException e) {
            log.error("search service error,msg:{}", e.getMessage());
            return null;
        }
    }
}
