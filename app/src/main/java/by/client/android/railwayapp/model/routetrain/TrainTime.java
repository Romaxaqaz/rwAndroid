package by.client.android.railwayapp.model.routetrain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Модель описания времени прибытия и отправления
 *
 * @author PRV
 */
public class TrainTime implements Parcelable {

    /**
     * Время прибытия
     */
    private String arrival;

    /**
     * Время отправления
     */
    private String arrived;

    public TrainTime(String arrival, String arrived) {
        this.arrival = arrival;
        this.arrived = arrived;
    }

    public String getArrival() {
        return arrival;
    }

    public String getArrived() {
        return arrived;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.arrival);
        dest.writeString(this.arrived);
    }

    private TrainTime(Parcel in) {
        this.arrival = in.readString();
        this.arrived = in.readString();
    }

    public static final Parcelable.Creator<TrainTime> CREATOR = new Parcelable.Creator<TrainTime>() {
        @Override
        public TrainTime createFromParcel(Parcel source) {
            return new TrainTime(source);
        }

        @Override
        public TrainTime[] newArray(int size) {
            return new TrainTime[size];
        }
    };
}
