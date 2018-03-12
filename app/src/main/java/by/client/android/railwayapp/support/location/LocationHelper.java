package by.client.android.railwayapp.support.location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;

/**
 * Класс для определения геопозиции
 *
 * TODO доработать по-человечески
 * TODO раскинуть работу с UI (AlertDialog)
 * TODO добавть проверку на выключенный GPS
 *
 * @author PRV
 */
public class LocationHelper {

    private static Logger logger = Logger.getLogger(LocationHelper.class.getName());

    private final static int ALL_PERMISSIONS_RESULT = 101;

    // геопозиция по умолчанию (Минск)
    private final static double DEFAULT_LATITUDE = 53.9;
    private final static double DEFAULT_LONGITUDE = 27.5667;

    private final Activity activity;
    private LocationManager locationManager;
    private List<String> permissions = new ArrayList<>();
    private List<String> permissionsToRequest;
    private boolean isNetwork;

    public LocationHelper(final Activity activity) {
        this.activity = activity;

        initLocationManager();
    }

    private void initLocationManager() {
        locationManager = (LocationManager) activity.getSystemService(Service.LOCATION_SERVICE);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);
    }

    /**
     * Опредедяет доступно ли определение местоположения
     */
    public boolean isPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!permissionsToRequest.isEmpty()) {
                requestPermission();
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * Запрпашивает разрешение на доступ к определению геопозиции
     */
    public void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(
                permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }
    }

    /**
     * Возвращает текущий город по координатам или пустую строку, если город не определен
     *
     * @param context контекст приложения
     * @param latitude широта
     * @param longitude долгота
     */
    public String getCurrentCity(Context context, double latitude, double longitude) {
        try {
            Geocoder geoCoder = new Geocoder(context, Locale.getDefault());
            List<Address> address = geoCoder.getFromLocation(latitude, longitude, 1);
            return address.get(0).getLocality();
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Can not receive current location", e);
        }
        return "";
    }

    /**
     * Возвращает текущий город согласно геопозиции
     *
     * <p>Если город получить не удается возвращается пустая строка</p>
     *
     * @return Название города или пустая строка, если годор определить не удалось
     */
    public String getCity() {
        try {
            Location location = getLocation();
            return location == null ? "" : getCurrentCity(activity, location.getLatitude(), location.getLongitude());
        }
        catch (SecurityException e) {
            logger.log(Level.SEVERE, "Can not receive current location", e);
        }
        return "";
    }

    private Location getLocation() {
        String provider = isNetwork ? LocationManager.NETWORK_PROVIDER : LocationManager.GPS_PROVIDER;
        try {
            return locationManager.getLastKnownLocation(provider);
        }
        catch (SecurityException e) {
            logger.log(Level.SEVERE, "Can not receive current location", e);
        }
        return getDefaultLocation(provider);
    }

    private Location getDefaultLocation(String provider) {
        Location location = new Location(provider);
        location.setLatitude(DEFAULT_LATITUDE);
        location.setLongitude(DEFAULT_LONGITUDE);
        return location;
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle("GPS is not Enabled!");
        alertDialog.setMessage("Do you want to turn on GPS?");
        alertDialog.setPositiveButton("Yes", new DialogPositiveListener());
        alertDialog.setNegativeButton("No", new DialogNegativeListener());
        alertDialog.show();
    }

    private List<String> findUnAskedPermissions(List<String> wanted) {
        List<String> result = new ArrayList<>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }
        return result;
    }

    private boolean hasPermission(String permission) {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    private void toLocationSettings() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivity(intent);
    }

    private class DialogPositiveListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            toLocationSettings();
        }
    }

    private class DialogNegativeListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    }
}
