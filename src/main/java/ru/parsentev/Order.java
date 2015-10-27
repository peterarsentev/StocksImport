package ru.parsentev;

/**
 * TODO: comment
 * @author parsentev
 * @since 24.10.2015
 */
public class Order {
	public enum Type {
		SELL, BUY;
	}
	public final String book;
	public final Type type;
	public final float price;
	public final int volume;
	public final int id;

	public Order(String book, Type type, float price, int volume, int id) {
		this.book = book;
		this.type = type;
		this.price = price;
		this.volume = volume;
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Order order = (Order) o;

		if (id != order.id) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return id;
	}
}
