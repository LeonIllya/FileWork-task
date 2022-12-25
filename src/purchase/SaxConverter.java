package purchase;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class SaxConverter extends DefaultHandler {
    StringBuilder currentValue = new StringBuilder();
    List<Product> result;
    Product product;

    public List<Product> getResult() {
        return result;
    }

    @Override
    public void startDocument() {
        result = new ArrayList<>();
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        currentValue.setLength(0);

        if (qName.equalsIgnoreCase("product")) {

            product = new Product();

            String id = attributes.getValue("id");
            product.setId(Long.valueOf(id));
        }

        if (qName.equalsIgnoreCase("price")) {
            String currency = attributes.getValue("currency");
            product.setCurrency(currency);
        }

    }

    public void endElement(String uri,
                           String localName,
                           String qName) {

        if (qName.equalsIgnoreCase("name")) {
            product.setName(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("quantity")) {
            product.setQuantity(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("price")) {
            product.setPrice(new BigDecimal(currentValue.toString())
                    .setScale(2, RoundingMode.CEILING)
            );
        }

        if (qName.equalsIgnoreCase("product")) {
            result.add(product);
        }
    }

    public void characters(char[] ch, int start, int length) {
        currentValue.append(ch, start, length);
    }
}
