package by.client.android.railwayapp.ui.scoreboard;

import java.util.Map;

import by.client.android.railwayapp.R;
import by.client.android.railwayapp.support.common.MapBuilder;

/**
 * Класс конвертер для преобразования строки типа поезда в иконку
 *
 * @author ROMAN PANTELEEV
 */
public class TrainTypeToImage {

    private final Map<String, Integer> imageDictionary = new MapBuilder<String, Integer>()
        .put("b-pic train_type international", R.drawable.interational)
        .put("b-pic train_type regional_economy", R.drawable.region_econom)
        .put("b-pic train_type regional_business", R.drawable.region_business)
        .put("b-pic train_type interregional_economy", R.drawable.interregional_economy)
        .put("b-pic train_type interregional_business", R.drawable.interregional_business)
        .put("b-pic train_type airport", R.drawable.airport)
        .put("b-pic train_type city", R.drawable.city)
        .build();

    public Integer convert(String type) {
        if (type != null && imageDictionary.containsKey(type)) {
            return imageDictionary.get(type);
        }
        return R.drawable.default_line;
    }

    public Integer convertIfContain(String type) {
        for (Map.Entry<String, Integer> item : imageDictionary.entrySet()) {
            if (item.getKey().contains(type)) {
                return item.getValue();
            }
        }
        return R.drawable.default_line;
    }
}
