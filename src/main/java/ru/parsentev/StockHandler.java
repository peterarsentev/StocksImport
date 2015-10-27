package ru.parsentev;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TODO: comment
 * @author parsentev
 * @since 24.10.2015
 */
public class StockHandler extends DefaultHandler {
//	public final List<Attributes> list = new ArrayList<>(1000);

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("AddOrder".equals(qName)) {
//			list.add(attributes);
//			final Order order = new Order(
//					attributes.getValue(0),
//					"SELL".equals(attributes.getValue(1)) ? Order.Type.SELL : Order.Type.BUY,
//					Float.valueOf(attributes.getValue(2)),
//					Integer.valueOf(attributes.getValue(3)),
//					Integer.valueOf(attributes.getValue(4))
//			);
		}
	}
}
