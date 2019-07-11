package com.yuu.ymall.web.api.test;

import com.yuu.ymall.web.api.domain.ESItem;
import com.yuu.ymall.web.api.repositories.ItemRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Yuu
 * @classname EsTest
 * @date 2019/6/30 12:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context-elasticsearch.xml")
public class EsTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemRepository itemRepository;

    /**
     * 创建索引
     *
     * @throws Exception
     */
    @Test
    public void createIndex() throws Exception {
        elasticsearchTemplate.createIndex(ESItem.class);
        //System.out.println("创建索引和映射关系成功");
        //elasticsearchTemplate.putMapping(ESItem.class);
    }

    /**
     * 新增文档
     */
    @Test
    public void insertDocument() {
        for (long i = 0l; i <= 10l; i++) {
            ESItem esItem = new ESItem();
            esItem.setId(i);
            esItem.setCid(1193l);
            esItem.setProductName("小米手机1");
            esItem.setSubTitle("小米手机真好1");
            esItem.setSalePrice(299d * i);
            esItem.setProductId(150635087972564l);
            esItem.setPicUrl("https://i.loli.net/2018/07/13/5b48ac7766d98.png");
            esItem.setOrderNum(100 * (int)i);
            esItem.setLimit((int)i);
            itemRepository.save(esItem);
        }
    }

    /**
     * 高亮显示
     */
    @Test
    public void heiglight() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("subTitle", "小米"))
                .withHighlightFields(new HighlightBuilder.Field("productName")).build();
        AggregatedPage<ESItem> esItems = elasticsearchTemplate.queryForPage(searchQuery, ESItem.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> aClass, Pageable pageable) {
                List<ESItem> esItems = new ArrayList<>();
                for (SearchHit hit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    ESItem esItem = new ESItem();
                    HighlightField productName = hit.getHighlightFields().get("productName");
                    if (productName != null) {
                        esItem.setProductName(productName.fragments()[0].toString());
                    }
                    HighlightField subTitle = hit.getHighlightFields().get("subTitle");
                    if (subTitle != null) {
                        esItem.setSubTitle(subTitle.fragments()[0].toString());
                    }
                    esItems.add(esItem);
                }
                if (esItems.size() > 0) {
                    return new AggregatedPageImpl<>((List<T>) esItems);
                }
                return null;
            }
        });
        esItems.forEach(esItem -> System.out.println(esItem.getProductName()));
    }

    @Test
    public void catePageTest() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("cid", 1193));
        queryBuilder.withQuery(QueryBuilders.rangeQuery("salePrice").gte(1).lte(3000));
        queryBuilder.withPageable(PageRequest.of(0, 2));

        // 执行搜索
        Page<ESItem> search = elasticsearchTemplate.queryForPage(queryBuilder.build(), ESItem.class);
        System.out.println(search.getTotalElements());
        for (ESItem esItem : search) {
            System.out.println(esItem.getProductName());
        }
    }

    @Test
    public void cateTest() {
        // ElasticSearch 查询
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("cid", "1193"));
       /* if (priceGt != null && priceLt != null) {
            queryBuilder.withQuery(QueryBuilders.rangeQuery("price").gt(priceGt).lt(priceLt));
        }
        if (StringUtils.isNotBlank(categoryProductPageInfo.getSort())) {
            if ("2".equals(sort)) {
                queryBuilder.withSort(SortBuilders.fieldSort("orderNum").order(SortOrder.DESC));
            } else if ("3".equals(sort)) {
                queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
            } else if ("4".equals(sort)) {
                queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
            }
        }*/
        queryBuilder.withPageable(PageRequest.of(0, 20));

        // 执行搜索
        Page<ESItem> search = itemRepository.search(queryBuilder.build());
        List<ESItem> esItems = search.getContent();
    }
}
