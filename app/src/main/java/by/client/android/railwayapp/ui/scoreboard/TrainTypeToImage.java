package by.client.android.railwayapp.ui.scoreboard;

import java.util.HashMap;
import java.util.Map;

import by.client.android.railwayapp.R;

/**
 * Класс конвертер для преобразования строки типа поезда в иконку
 *
 * @author ROMAN PANTELEEV
 */
public class TrainTypeToImage {

    private final Map<String, Integer> imageDictionary = new HashMap<>();

    public TrainTypeToImage() {
        imageDictionary.put("b-pic train_type international", R.drawable.interational);
        imageDictionary.put("b-pic train_type regional_economy", R.drawable.region_econom);
        imageDictionary.put("b-pic train_type regional_business", R.drawable.region_business);
        imageDictionary.put("b-pic train_type interregional_economy", R.drawable.interregional_economy);
        imageDictionary.put("b-pic train_type interregional_business", R.drawable.interregional_business);
        imageDictionary.put("b-pic train_type airport", R.drawable.airport);
        imageDictionary.put("b-pic train_type city", R.drawable.city);
    }

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
