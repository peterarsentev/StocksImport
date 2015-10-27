package ru.parsentev;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * TODO: comment
 * @author parsentev
 * @since 24.10.2015
 */
public class Book {
	private final Collection<Order> orders;

	private static final Comparator<Float> ASC = new Comparator<Float>() {
		@Override
		public int compare(Float o1, Float o2) {
			return o1.compareTo(o2);
		}
	};

	private static final Comparator<Float> DESC = new Comparator<Float>() {
		@Override
		public int compare(Float o1, Float o2) {
			return o2.compareTo(o1);
		}
	};

	public Book(final Collection<Order> orders) {
		this.orders = orders;
	}

	public void calculate() {
		Map<Float, Order> sell = new TreeMap<Float, Order>(DESC);
		Map<Float, Order> buy = new TreeMap<Float, Order>(ASC);
		for (Order order : orders) {
			this.add(order.type == Order.Type.BUY ? buy : sell, order);
		}
		this.show(sell, buy);
	}

	public void add(final Map<Float, Order> map, Order order) {
		Order find = map.get(order.price);
		if (find != null) {
			map.put(find.price, new Order(find.book, find.type, find.price, find.volume + order.volume, find.id));
		} else {
			map.put(order.price, order);
		}
	}

	public void show(Map<Float, Order> sell, Map<Float, Order> buy) {
		StringBuilder builder = new StringBuilder();
		for (Order order : sell.values()) {
			builder.append(String.format("\t\t%5s %7s\n", order.price, order.volume));
		}
		for (Order order : buy.values()) {
			builder.append(String.format("%7s %5s\n", order.volume, order.price));
		}
		System.out.println(builder);
	}
}
