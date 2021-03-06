package com.yiping.stock.model;

// Generated 2016/1/19 �U�� 10:34:22 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yiping.product.model.Product100;

/**
 * Stock101 generated by hbm2java
 */
@Entity
@Table(name = "stock101", catalog = "yiping")
public class Stock101 implements java.io.Serializable {

	private int stock101Id;
	private Product100 product100;
	private Stock100 stock100;
	private Integer productNum;

	public Stock101() {
	}

	public Stock101(Product100 product100, Stock100 stock100, Integer productNum) {
		this.product100 = product100;
		this.stock100 = stock100;
		this.productNum = productNum;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "STOCK101_ID", unique = true, nullable = false)
	public int getStock101Id() {
		return this.stock101Id;
	}

	public void setStock101Id(int stock101Id) {
		this.stock101Id = stock101Id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT100_ID", nullable = false)
	public Product100 getProduct100() {
		return this.product100;
	}

	public void setProduct100(Product100 product100) {
		this.product100 = product100;
	}
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STOCK100_ID", nullable = false)
	public Stock100 getStock100() {
		return this.stock100;
	}

	public void setStock100(Stock100 stock100) {
		this.stock100 = stock100;
	}

	@Column(name = "PRODUCT_NUM", nullable = false)
	public Integer getProductNum() {
		return this.productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

}
