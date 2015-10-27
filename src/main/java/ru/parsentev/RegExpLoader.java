package ru.parsentev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * TODO: comment
 * @author parsentev
 * @since 24.10.2015
 */
public class RegExpLoader {
	final Map<String, HashMap<Integer, Order>> orders = new HashMap<String, HashMap<Integer, Order>>();

	public void load() throws IOException, ExecutionException, InterruptedException {
		try (BufferedReader br = new BufferedReader(new FileReader("orders.xml"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("<A")) {
					final Order order = this.parse(line, true);
					HashMap<Integer, Order> list = orders.get(order.book);
					if (list == null) {
						list = new HashMap<Integer, Order>();
						orders.put(order.book, list);
					}
					list.put(order.id, order);
				} else if (line.startsWith("<D")) {
					final Order order = this.parse(line, false);
					orders.get(order.book).remove(order.id);
				}
			}
		}
		OrderSplitter orderSplitter = new OrderSplitter(orders);
		orderSplitter.match();
	}

	public Order parse(final String text, boolean add) {
		boolean start = false;
		int pos = -1;
		String[] values = new String[5];
		int current = 0;
		for (int i=0;i!=text.length();++i) {
			if (text.charAt(i) == '\"') {
				if (start) {
					values[current++] = text.substring(pos+1, i);
					start = false;
				} else {
					start = true;
				}
				pos = i;
			}
		}
		if (add) {
			return new Order(
					values[0],
					"SELL".equals(values[1]) ? Order.Type.SELL : Order.Type.BUY,
					Float.valueOf(values[2]),
					Integer.valueOf(values[3]),
					Integer.valueOf(values[4])
			);
		} else {
			return new Order(values[0], Order.Type.BUY, 0f, 0, Integer.valueOf(values[1]));
		}
	}

	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		long start = System.currentTimeMillis();
		final RegExpLoader loader = new RegExpLoader();
		loader.load();
		System.out.println(String.format("time : %s s", (System.currentTimeMillis() - start)));
	}
}
