package by.client.android.railwayapp.model.routetrain;

import android.os.Parcel;
import android.os.Parcelable;
import by.client.android.railwayapp.model.ParcelableUtils;

/**
 * Параметры поезда
 *
 * @author ROMAN PANTELEEV
 */
public class TrainParameters implements Parcelable {

    /**
     * Возможность электронной регистрации
     */
    private Boolean isElectronicRegistration;

    /**
     * Фирменный поезд
     */
    private Boolean isCorporateTrain;

    /**
     * Скорый поезд
     */
    private Boolean isExpressTrain;

    public TrainParameters(Boolean isElectronicRegistration, Boolean isCorporateTrain, Boolean isExpressTrain) {
        this.isElectronicRegistration = isElectronicRegistration;
        this.isCorporateTrain = isCorporateTrain;
        this.isExpressTrain = isExpressTrain;
    }

    public Boolean getElectronicRegistration() {
        return isElectronicRegistration;
    }

    public Boolean getCorporateTrain() {
        return isCorporateTrain;
    }

    public Boolean getExpressTrain() {
        return isExpressTrain;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.isElectronicRegistration);
        dest.writeValue(this.isCorporateTrain);
        dest.writeValue(this.isExpressTrain);
    }

    private TrainParameters(Parcel in) {
        this.isElectronicRegistration = ParcelableUtils.readValue(in, Boolean.class);
        this.isCorporateTrain = ParcelableUtils.readValue(in, Boolean.class);
        this.isExpressTrain = ParcelableUtils.readValue(in, Boolean.class);
    }

    public static final Parcelable.Creator<TrainParameters> CREATOR = new Parcelable.Creator<TrainParameters>() {
        @Override
        public TrainParameters createFromParcel(Parcel source) {
            return new TrainParameters(source);
        }

        @Override
        public TrainParameters[] newArray(int size) {
            return new TrainParameters[size];
        }
    };
}
