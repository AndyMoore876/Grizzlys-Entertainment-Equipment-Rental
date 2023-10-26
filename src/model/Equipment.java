
public class Equipment implements Serializable{

    private String equipmentId;
    private Sting equipmentName;
    private String equipmentType;
    private String eventId;
    private Boolean rented;
    private Date dateRented;
    private Date returnDate;
    private float costPerDay;
    private float totalCost;

    public Equipment() {
        this.equipmentId = null;
        this.equipmentName = null;
        this.equipmentType = null;
        this.eventId = null;
        this.rented = null;
        this.dateRented = null;
        this.returnDate = null;
        this.costPerDay = null;
        this.totalCost = null;
    }

    public Equipment(String equipmentId, Sting equipmentName, String equipmentType, String eventId, Boolean rented, Date dateRented, Date returnDate, float costPerDay, float totalCost) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentType = equipmentType;
        this.eventId = eventId;
        this.rented = rented;
        this.dateRented = dateRented;
        this.returnDate = returnDate;
        this.costPerDay = costPerDay;
        this.totalCost = totalCost;
    }

    //setters and getters
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Sting getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(Sting equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public Date getDateRented() {
        return dateRented;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public float getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(float costPerDay) {
        this.costPerDay = costPerDay;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
    //end of setters and getters


    @java.lang.Override
    public java.lang.String toString() {
        return "Equipment{" +
                "equipmentId='" + equipmentId + '\'' +
                ", equipmentName=" + equipmentName +
                ", equipmentType='" + equipmentType + '\'' +
                ", eventId='" + eventId + '\'' +
                ", rented=" + rented +
                ", dateRented=" + dateRented +
                ", returnDate=" + returnDate +
                ", costPerDay=" + costPerDay +
                ", totalCost=" + totalCost +
                '}';
    }

    public void rent(String customerID, String eventId){
        if (this.rented == true){
            JOptionPane.
        }
    }
}