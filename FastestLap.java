public class FastestLap {
    private String driverName;
    private int vehicleIdx;
    private float lapTime;

    public FastestLap() {}

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    
    public int getVehicleIdx() {
        return vehicleIdx;
    }
    public void setVehicleIdx(int vehicleIdx) {
        this.vehicleIdx = vehicleIdx;
    }
    public float getLapTime() {
        return lapTime;
    }
    public void setLapTime(float lapTime) {
        this.lapTime = lapTime;
    }

    //@TODO implement ToString
    public String toString() {
        String string = 
          "Driver Name : " + driverName +
        "\nLap Time    : " + lapTime;
        return string; 
    }

}
