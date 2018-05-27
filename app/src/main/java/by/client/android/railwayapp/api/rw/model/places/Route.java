package by.client.android.railwayapp.api.rw.model.places;

/**
 * Model to represent the path
 *
 * @author PRV
 */
public class Route {

    private String from;
    private String to;
    private String startDate;
    private String startDateForRequest;
    private String startTime;
    private String endDate;
    private String endTime;
    private String timeInWay;
    private String startDateFormatted;
    private Boolean hidden;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDateForRequest() {
        return startDateForRequest;
    }

    public void setStartDateForRequest(String startDateForRequest) {
        this.startDateForRequest = startDateForRequest;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeInWay() {
        return timeInWay;
    }

    public void setTimeInWay(String timeInWay) {
        this.timeInWay = timeInWay;
    }

    public String getStartDateFormatted() {
        return startDateFormatted;
    }

    public void setStartDateFormatted(String startDateFormatted) {
        this.startDateFormatted = startDateFormatted;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

}
