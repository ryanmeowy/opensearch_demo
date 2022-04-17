package com.ryan.opensearch_demo.pojo;

import lombok.Data;

/**
 * @author ryan
 */
@Data
public class SearchVO {
    private String brandEnglishName;
    private String brandName;
    private String categoryName;
    private String materialNo;
    private String parameterName;
    private String parameterValue;
    private String productName;
    private String seriesName;
    private String shortName;
    private Integer smId;
}
