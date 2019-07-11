package com.yuu.ymall.web.admin.commons.es;

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
     * 商品名称
     */
    @Field(type = FieldType.text, analyzer = "ik_max_word")
    private String productName;

    /**
     * 商品副标题
     */
    @Field(type = FieldType.text, analyzer = "ik_max_word")
    private String subTitle;

    /**
     * 商品 id
     */
    @Field(type = FieldType.Long)
    private Long productId;

    /**
     * 分类 id
     */
    @Field(type = FieldType.Long)
    private Long cid;

    /**
     * 商品售价
     */
    @Field(type = FieldType.Double)
    private Double salePrice;

    /**
     * 商品大图 1 张
     */
    @Field(type = FieldType.keyword)
    private String picUrl;

    /**
     * 商品销量
     */
    @Field(type = FieldType.Integer)
    private Integer orderNum;

    /**
     * 商品限制购买数量
     */
    @Field(type = FieldType.Integer)
    private Integer limit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
