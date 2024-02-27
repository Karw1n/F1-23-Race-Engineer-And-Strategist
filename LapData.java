
public class LapData extends Packet {
    private String driverName;
    private int lastLapTimeInMS;
    private int currentLapTimeInMS;
    private int sector1TimeInMS;
    private int sector1TimeMinutes;
    private int sector2TimeInMS;
    private int sector2TimeMinutes;
    private int deltaToCarInFrontInMS;
    private int deltaToRaceLeaderInMS;
    private float lapDistance;
    private float totalDistance;
    private float safetyCarDelta;
    private int carPosition;
    private int currentLapNum;
    private int pitStatus;
    private int numPitStops;
    private int sector;
    private int currentLapInvalid;
    private int penalties;
    private int totalWarnings;
    private int cornerCuttingWarnings;
    private int numUnservedDriveThroughPens;
    private int numUnservedStopGoPens;
    private int gridPosition;
    private int driverStatus;
    private int resultStatus;
    private int pitLaneTimerActive;
    private int pitLaneTimeInLaneInMS;
    private int pitStopTimerInMS;
    private int pitStopShouldServePen;

    public LapData() {}
    
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }


    public int getLastLapTimeInMS() {
        return lastLapTimeInMS;
    }

    public void setLastLapTimeInMS(int lastLapTimeInMS) {
        this.lastLapTimeInMS = lastLapTimeInMS;
    }

    public int getCurrentLapTimeInMS() {
        return currentLapTimeInMS;
    }

    public void setCurrentLapTimeInMS(int currentLapTimeInMS) {
        this.currentLapTimeInMS = currentLapTimeInMS;
    }

    public int getSector1TimeInMS() {
        return sector1TimeInMS;
    }

    public void setSector1TimeInMS(int sector1TimeInMS) {
        this.sector1TimeInMS = sector1TimeInMS;
    }

    public int getSector1TimeMinutes() {
        return sector1TimeMinutes;
    }

    public void setSector1TimeMinutes(int sector1TimeMinutes) {
        this.sector1TimeMinutes = sector1TimeMinutes;
    }

    public int getSector2TimeInMS() {
        return sector2TimeInMS;
    }

    public void setSector2TimeInMS(int sector2TimeInMS) {
        this.sector2TimeInMS = sector2TimeInMS;
    }

    public int getSector2TimeMinutes() {
        return sector2TimeMinutes;
    }

    public void setSector2TimeMinutes(int sector2TimeMinutes) {
        this.sector2TimeMinutes = sector2TimeMinutes;
    }

    public int getDeltaToCarInFrontInMS() {
        return deltaToCarInFrontInMS;
    }

    public void setDeltaToCarInFrontInMS(int deltaToCarInFrontInMS) {
        this.deltaToCarInFrontInMS = deltaToCarInFrontInMS;
    }

    public int getDeltaToRaceLeaderInMS() {
        return deltaToRaceLeaderInMS;
    }

    public void setDeltaToRaceLeaderInMS(int deltaToRaceLeaderInMS) {
        this.deltaToRaceLeaderInMS = deltaToRaceLeaderInMS;
    }

    public float getLapDistance() {
        return lapDistance;
    }

    public void setLapDistance(float lapDistance) {
        this.lapDistance = lapDistance;
    }

    public float getTotalDistance() {
        return totalDistance;
    }
    
    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public float getSafetyCarDelta() {
        return safetyCarDelta;
    }

    public void setSafetyCarDelta(float safetyCarDelta) {
        this.safetyCarDelta = safetyCarDelta;
    }

    public int getCarPosition() {
        return carPosition;
    }

    public void setCarPosition(int carPosition) {
        this.carPosition = carPosition;
    }

    public int getCurrentLapNum() {
        return currentLapNum;
    }

    public void setCurrentLapNum(int currentLapNum) {
        this.currentLapNum = currentLapNum;
    }

    public int getPitStatus() {
        return pitStatus;
    }

    public void setPitStatus(int pitStatus) {
        this.pitStatus = pitStatus;
    }

    public int getNumPitStops() {
        return numPitStops;
    }

    public void setNumPitStops(int numPitStops) {
        this.numPitStops = numPitStops;
    }

    public int getSector() {
        return sector;
    }

    public void setSector(int sector) {
        this.sector = sector;
    }

    public int getCurrentLapInvalid() {
        return currentLapInvalid;
    }

    public void setCurrentLapInvalid(int currentLapInvalid) {
        this.currentLapInvalid = currentLapInvalid;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public int getTotalWarnings() {
        return totalWarnings;
    }

    public void setTotalWarnings(int totalWarnings) {
        this.totalWarnings = totalWarnings;
    }

    public int getCornerCuttingWarnings() {
        return cornerCuttingWarnings;
    }

    public void setCornerCuttingWarnings(int cornerCuttingWarnings) {
        this.cornerCuttingWarnings = cornerCuttingWarnings;
    }

    public int getNumUnservedDriveThroughPens() {
        return numUnservedDriveThroughPens;
    }

    public void setNumUnservedDriveThroughPens(int numUnservedDriveThroughPens) {
        this.numUnservedDriveThroughPens = numUnservedDriveThroughPens;
    }

    public int getNumUnservedStopGoPens() {
        return numUnservedStopGoPens;
    }

    public void setNumUnservedStopGoPens(int numUnservedStopGoPens) {
        this.numUnservedStopGoPens = numUnservedStopGoPens;
    }

    public int getGridPosition() {
        return gridPosition;
    }

    public void setGridPosition(int gridPosition) {
        this.gridPosition = gridPosition;
    }

    public int getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(int driverStatus) {
        this.driverStatus = driverStatus;
    }

    public int getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(int resultStatus) {
        this.resultStatus = resultStatus;
    }

    public int getPitLaneTimerActive() {
        return pitLaneTimerActive;
    }

    public void setPitLaneTimerActive(int pitLaneTimerActive) {
        this.pitLaneTimerActive = pitLaneTimerActive;
    }

    public int getPitLaneTimeInLaneInMS() {
        return pitLaneTimeInLaneInMS;
    }

    public void setPitLaneTimeInLaneInMS(int pitLaneTimeInLaneInMS) {
        this.pitLaneTimeInLaneInMS = pitLaneTimeInLaneInMS;
    }

    public int getPitStopTimerInMS() {
        return pitStopTimerInMS;
    }

    public void setPitStopTimerInMS(int pitStopTimerInMS) {
        this.pitStopTimerInMS = pitStopTimerInMS;
    }

    public int getPitStopShouldServePen() {
        return pitStopShouldServePen;
    }

    public void setPitStopShouldServePen(int pitStopShouldServePen) {
        this.pitStopShouldServePen = pitStopShouldServePen;
    }

    public String toString() {
        String string = 
            "Driver Name                      : " + driverName + 
            "\nLastLapTimeInMS                  : " + lastLapTimeInMS + 
            "\nCurrentLapTimeInMS               : " + currentLapTimeInMS + 
            "\nSector1TimeInMS                  : " + sector1TimeInMS + 
            "\nSector1TimeMinutes               : " + sector1TimeMinutes + 
            "\nSector2TimeInMS                  : " + sector2TimeInMS + 
            "\nSector2TimeMinutes               : " + sector2TimeMinutes +
            "\nDeltaToCarInFrontInMS            : " + deltaToCarInFrontInMS + 
            "\nDeltaToRaceLeaderInMS            : "  + deltaToRaceLeaderInMS + 
            "\nLapDistance                      : " + lapDistance + 
            "\nTotalDistance                    : " + totalDistance + 
            "\nSafetyCarDelta                   : " + safetyCarDelta + 
            "\nCarPosition                      : " + carPosition + 
            "\nCurrentLapNum                    : " + currentLapNum + 
            "\nPit Status                       : " + pitStatus + 
            "\nNum Of Pit Stops                 : " + numPitStops + 
            "\nSector                           : " + sector + 
            "\nCurrent Lap Invalid              : " + currentLapInvalid + 
            "\nPenalties                        : " + penalties + 
            "\nTotal Warnings                   : " + totalWarnings + 
            "\nCorner Cutting Warnings          : " + cornerCuttingWarnings + 
            "\nNum Unserved Drive Through Pens  : " + numUnservedDriveThroughPens + 
            "\nNum Unserved Stop Go Pens        : " + numUnservedStopGoPens + 
            "\nGrid Position                    : " + gridPosition + 
            "\nDriver Status                    : " + driverStatus + 
            "\nResult Status                    : " + resultStatus + 
            "\nPit Lane Timer Active            : " + pitLaneTimerActive + 
            "\nPit Lane Time In MS              : " + pitLaneTimeInLaneInMS + 
            "\nPit Stop Timer In Ms             : " + pitStopTimerInMS + 
            "\nPit Stop Should serve Pen        : " + pitStopShouldServePen;
        return string;
    }

}
