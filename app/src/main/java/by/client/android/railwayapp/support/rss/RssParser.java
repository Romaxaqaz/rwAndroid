package by.client.android.railwayapp.support.rss;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Created by PanteleevRV on 15.02.2018.
 *
 * @author PRV
 */
public class RssParser implements RssReader<Article> {

    private static Logger logger = Logger.getLogger(RssParser.class.getName());

    private static final String ITEM = "item";

    private ArrayList<Article> articles;

    public RssParser() {
        articles = new ArrayList<>();
    }

    @Override
    public List<Article> parseRss(String url) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(url);
            NodeList valueList = document.getElementsByTagName(ITEM);

            for (int i = 0; i < valueList.getLength(); i++) {
                Element item = (Element) valueList.item(i);
                RssNodeParser rssNode = new RssNodeParser(item);

                articles.add(new Article()
                    .setTitle(rssNode.getTitle())
                    .setLink(rssNode.getLink())
                    .setCategories(rssNode.getCategory())
                    .setDescription(rssNode.getDescription())
                    .setPubDate(rssNode.getDate()));
            }
        }
        catch (ParserConfigurationException | SAXException exception) {
            logger.log(Level.SEVERE, "Error configuration DocumentBuilder for rss news parsing", exception);
        }
        catch (IOException exception) {
            logger.log(Level.SEVERE, String.format("Error rss news parsing form url %s", url), exception);
        }
        return articles;
    }

    private class RssNodeParser {

        private static final String TITLE = "title";
        private static final String LINK = "link";
        private static final String CATEGORY = "category";
        private static final String DESCRIPTION = "description";
        private static final String PUB_DATE = "pubDate";

        private static final String RSS_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

        Element element;

        RssNodeParser(Element element) {
            this.element = element;
        }

        public String getTitle() {
            return getValue(TITLE);
        }

        public String getLink() {
            return getValue(LINK);
        }

        public String getCategory() {
            return getValue(CATEGORY);
        }

        public String getDescription() {
            return getValue(DESCRIPTION);
        }

        public Date getDate() {
            try {
                return new SimpleDateFormat(RSS_DATE_FORMAT, Locale.ENGLISH).parse(getValue(PUB_DATE));
            }
            catch (ParseException e) {
                return null;
            }
        }

        public String getValue(String str) {
            NodeList nodeList = element.getElementsByTagName(str);
            return this.getElementValue(nodeList.item(0));
        }

        private String getElementValue(Node elem) {
            Node child;
            if (elem != null) {
                if (elem.hasChildNodes()) {
                    for (child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                        if (child.getNodeType() == Node.TEXT_NODE) {
                            return child.getNodeValue();
                        }
                    }
                }
            }
            return "";
        }
    }
}
