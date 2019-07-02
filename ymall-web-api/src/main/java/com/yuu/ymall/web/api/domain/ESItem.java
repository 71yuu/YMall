package com.yuu.ymall.web.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Elasticsearch 对应实体类
 *
 * @author by Yuu
 * @classname ESItem
 * @date 2019/6/29 21:39
 */
@Document(indexName = "item", type = "itemList", shards = 1, replicas = 0)
public class ESItem {
    /**
     * 商品 ID
     */
    @Id
    private Long id;

    /**
     * 商品标题
     */
    @Field(type = FieldType.text, analyzer = "ik_max_word")
    private String title;

    /**
     * 商品卖点
     */
    @Field(type = FieldType.text, analyzer = "ik_max_word")
    private String sellPoint;

    /**
     * 商品价格
     */
    @Field(type = FieldType.Double)
    private Double price;

    /**
     * 商品图片地址
     */
    @Field(type = FieldType.keyword)
    private String image;

    /**
     * 商品所属分类名称
     */
    @Field(type = FieldType.keyword)
    private String cname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
