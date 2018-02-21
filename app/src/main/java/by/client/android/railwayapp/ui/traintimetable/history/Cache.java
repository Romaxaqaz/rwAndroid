package by.client.android.railwayapp.ui.traintimetable.history;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.Context;

/**
 * Класс дял сохранения объектов в кэш
 *
 * @author PRV
 */
public class Cache<T> implements ObjectCache<T> {

    private static Logger logger = Logger.getLogger(Cache.class.getName());

    private Context context;
    private String key;

    public Cache(Context context, String key) {
        this.context = context;
        this.key = key;
    }

    @Override
    public void save(T object) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = context.openFileOutput(key, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException exception) {
            logger.log(Level.WARNING, "Error while saving query history cache", exception);
        }
    }

    @Override
    public T read() {
        try {
            FileInputStream fileInputStream = context.openFileInput(key);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (T) objectInputStream.readObject();
        }
        catch (ClassNotFoundException | IOException exception) {
            logger.log(Level.WARNING, "Error while reading query history cache", exception);
        }
        return null;
    }
}
