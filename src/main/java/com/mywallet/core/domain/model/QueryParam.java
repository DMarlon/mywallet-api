package com.mywallet.core.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class QueryParam {

	private static final List<String> directions = Collections.unmodifiableList(List.of("ASC", "DESC"));

	private int page;
	private int limit;
	private String order;
	private String direction;
	private String term;

	public QueryParam() {
		this.page = 1;
		this.limit = 20;
		this.order = "";
		this.direction = directions.get(0);
		this.term = "";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page > 0 ? page : 1;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {

		this.limit = limit > 0 ? limit : 1;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = Objects.isNull(order) ? null : order.trim();
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		if (Objects.isNull(direction))
			this.direction = direction;
		else {
			String formattedDirection = direction.trim().toUpperCase();
			this.direction = directions.contains(formattedDirection) ? formattedDirection : directions.get(0);
		}
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = Objects.isNull(term) ? null : term.trim();
	}

}
