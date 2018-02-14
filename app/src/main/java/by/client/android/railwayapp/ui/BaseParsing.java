package by.client.android.railwayapp.ui;

import java.util.List;

import org.jsoup.nodes.Element;


/**
 * Базовый класс парсинга страниц
 *
 * @author PRV
 */
public abstract class BaseParsing<Result, Param> {

    protected static final String EMPTY_STRING = "";
    protected static final String TABLE = "table";
    protected static final String TR = "tr";

    private Param param;

    public BaseParsing(Param param) {
        this.param = param;
    }

    protected abstract List<Result> pars() throws Exception;

    protected Param getParam() {
        return param;
    }

    protected String checkEmpty(Element element) {
        return element == null ? "" : element.text();
    }
}
