package com.yuu.ymall.web.api.repositories;

import com.yuu.ymall.web.api.domain.ESItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author by Yuu
 * @classname ItemRepository
 * @date 2019/6/30 13:12
 */
public interface ItemRepository extends ElasticsearchRepository<ESItem, Long> {
}
