package com.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.HotelMapper;
import com.demo.dto.PageResult;
import com.demo.dto.RequestParams;
import com.demo.entity.Hotel;
import com.demo.entity.HotelDoc;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {

    @Resource
    private ElasticsearchRestTemplate restTemplate;

    @Override
    public PageResult<HotelDoc> search(RequestParams params) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        // 1.布尔查询
        buildQuery(params, nativeSearchQueryBuilder);

        // 2.查询结果排序
        String sortBy = params.getSortBy();
        FieldSortBuilder sortBuilder = null;
        switch (sortBy) {
            case "score":
                sortBuilder = SortBuilders.fieldSort("score").order(SortOrder.DESC);
                nativeSearchQueryBuilder.withSort(sortBuilder);
                break;
            case "price":
                sortBuilder = SortBuilders.fieldSort("price").order(SortOrder.ASC);
                nativeSearchQueryBuilder.withSort(sortBuilder);
                break;
        }

        // 3.查询结果分页
        Pageable pageable = PageRequest.of(params.getPage() - 1, params.getSize());
        nativeSearchQueryBuilder.withPageable(pageable);

        // 4.查询结果高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("name").requireFieldMatch(false);
        nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);

        // 5.执行查询
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<HotelDoc> searchHits = restTemplate.search(nativeSearchQuery, HotelDoc.class);

        // 6.封装响应
        long total = searchHits.getTotalHits();
        List<HotelDoc> hotelDocs = new ArrayList<>();
        for (SearchHit<HotelDoc> hit : searchHits.getSearchHits()) {
            List<String> field = hit.getHighlightField("name");
            if (field.size() > 0) {
                hit.getContent().setName(field.get(0));
            }
            hotelDocs.add(hit.getContent());
        }
        return new PageResult<>(total, hotelDocs);
    }

    @Override
    public Map<String, List<String>> getFilters(RequestParams params) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        buildQuery(params, nativeSearchQueryBuilder);

        nativeSearchQueryBuilder.addAggregation(
                AggregationBuilders.terms("brandAgg").field("brand").size(100));
        nativeSearchQueryBuilder.addAggregation(
                AggregationBuilders.terms("cityAgg").field("city").size(100));
        nativeSearchQueryBuilder.addAggregation(
                AggregationBuilders.terms("starAgg").field("starName").size(100));

        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<HotelDoc> searchHits = restTemplate.search(nativeSearchQuery, HotelDoc.class);
        Aggregations aggregations = searchHits.getAggregations();
        Map<String, List<String>> filters = new HashMap<>(3);
        assert aggregations != null;
        // 解析品牌
        List<String> brandList = getAggregationByName(aggregations, "brandAgg");
        filters.put("brand", brandList);
        // 解析城市
        List<String> cityList = getAggregationByName(aggregations, "cityAgg");
        filters.put("city", cityList);
        // 解析星级
        List<String> starList = getAggregationByName(aggregations, "starAgg");
        filters.put("starName", starList);
        return filters;
    }

    private void buildQuery(RequestParams params, NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // 关键词
        String key = params.getKey();
        if (StringUtils.isEmpty(key)) {
            boolQuery.must(QueryBuilders.matchAllQuery());
        } else {
            boolQuery.must(QueryBuilders.matchQuery("all", key));
        }

        // 城市
        String city = params.getCity();
        if (!StringUtils.isEmpty(city)) {
            boolQuery.filter(QueryBuilders.termQuery("city", city));
        }

        // 品牌
        String brand = params.getBrand();
        if (!StringUtils.isEmpty(brand)) {
            boolQuery.filter(QueryBuilders.termQuery("brand", brand));
        }

        // 星级
        String starName = params.getStarName();
        if (!StringUtils.isEmpty(starName)) {
            boolQuery.filter(QueryBuilders.termQuery("starName", starName));
        }

        // 价格范围
        Integer minPrice = params.getMinPrice();
        Integer maxPrice = params.getMaxPrice();
        if (minPrice != null && maxPrice != null) {
            boolQuery.filter(QueryBuilders.rangeQuery("price").gte(minPrice).lte(maxPrice));
        }
        nativeSearchQueryBuilder.withQuery(boolQuery);
    }

    private List<String> getAggregationByName(Aggregations aggregations, String name) {
        Terms terms = aggregations.get(name);
        List<? extends Terms.Bucket> buckets = terms.getBuckets();
        List<String> list = new ArrayList<>(buckets.size());
        for (Terms.Bucket bucket : buckets) {
            String brandName = bucket.getKeyAsString();
            list.add(brandName);
        }
        return list;
    }
}
