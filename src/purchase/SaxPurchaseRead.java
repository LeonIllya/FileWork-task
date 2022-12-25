package purchase;

import org.xml.sax.SAXException;
import utils.Constants;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SaxPurchaseRead {

    private static final String PATH = Constants.RES_PATH + "\\productes.xml";

    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            SAXParser saxParser = factory.newSAXParser();

            SaxConverter handler = new SaxConverter();

            saxParser.parse(PATH, handler);

            AtomicInteger count = new AtomicInteger(0);

            List<Product> result = handler.getResult();
            result.forEach(employee ->
                    System.out.println("\n" + count.incrementAndGet() + ") " +
                            "ID: " + employee.getId() + "\n" +
                            employee.getName() + " " +
                            employee.getQuantity() + " " + "\n" +
                            "price: " + employee.getCurrency() + " " +
                            employee.getPrice()
                    )
            );
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}

