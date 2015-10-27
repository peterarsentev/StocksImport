package ru.parsentev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * TODO: comment
 * @author parsentev
 * @since 24.10.2015
 */
public class OrderSplitter {
	private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	private final Map<String, HashMap<Integer, Order>> list;

	public OrderSplitter(Map<String, HashMap<Integer, Order>> list) {
		this.list = list;
	}

	public void match() throws InterruptedException, ExecutionException {
		List<Future<Book>> futures = new ArrayList<>(list.size());
		for (final HashMap<Integer, Order> orders : list.values()) {
			futures.add(EXECUTOR.submit(new Callable<Book>() {
				@Override
				public Book call() throws Exception {
					Book book = new Book(orders.values());
					book.calculate();
					return book;
				}
			}));
		}
		for (Future<Book> future : futures) {
			future.get();
		}
		EXECUTOR.shutdown();
	}
}
