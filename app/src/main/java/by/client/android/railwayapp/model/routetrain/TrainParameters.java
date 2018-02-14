package by.client.android.railwayapp.model.routetrain;

import java.io.Serializable;

/**
 * Параметры поезда
 *
 * @author ROMAN PANTELEEV
 */
public class TrainParameters implements Serializable {

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
}
