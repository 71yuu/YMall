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
        //elasticsearchTemplate.createIndex(ESItem.class);
        //System.out.println("创建索引和映射关系成功");
        elasticsearchTemplate.putMapping(ESItem.class);
    }

    /**
     * 新增文档
     */
    @Test
    public void insertDocument() {
        ESItem esItem = new ESItem();
        esItem.setId(2l);
        esItem.setCname("手机");
        esItem.setTitle("小米手机");
        esItem.setSellPoint("正在热卖");
        esItem.setImage("xxx");
        esItem.setPrice(123d);
        itemRepository.save(esItem);
    }

    /**
     * 高亮显示
     */
    @Test
    public void heiglight() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("title", "小米"))
                .withHighlightFields(new HighlightBuilder.Field("title")).build();
        AggregatedPage<ESItem> esItems = elasticsearchTemplate.queryForPage(searchQuery, ESItem.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> aClass, Pageable pageable) {
                List<ESItem> esItems = new ArrayList<>();
                for (SearchHit hit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    ESItem esItem = new ESItem();
                    HighlightField itemTitle = hit.getHighlightFields().get("title");
                    if (itemTitle != null) {
                        esItem.setTitle(itemTitle.fragments()[0].toString());
                    }
                    esItems.add(esItem);
                }
                if (esItems.size() > 0) {
                    return new AggregatedPageImpl<>((List<T>) esItems);
                }
                return null;
            }
        });
        esItems.forEach(esItem -> System.out.println(esItem.getTitle()));
    }
}
