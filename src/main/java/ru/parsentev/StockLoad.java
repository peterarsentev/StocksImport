package ru.parsentev;

import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * TODO: comment
 * @author parsentev
 * @since 24.10.2015
 */
public class StockLoad {
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		InputStream xmlInput  = new FileInputStream("orders.xml");
		SAXParser saxParser = factory.newSAXParser();
		StockHandler handler = new StockHandler();
		saxParser.parse(xmlInput, handler);
//		OrderMather orderMather = new OrderMather(handler.list);
//		orderMather.match();
		System.out.println(String.format("time : %s s", (System.currentTimeMillis() - start)));
	}
}
