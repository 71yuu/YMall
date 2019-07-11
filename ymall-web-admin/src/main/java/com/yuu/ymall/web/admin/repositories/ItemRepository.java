package com.yuu.ymall.web.admin.repositories;

import com.yuu.ymall.web.admin.commons.es.ESItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author by Yuu
 * @classname ItemRepository
 * @date 2019/7/9 19:51
 */
public interface ItemRepository extends ElasticsearchRepository<ESItem, Long> {
}
