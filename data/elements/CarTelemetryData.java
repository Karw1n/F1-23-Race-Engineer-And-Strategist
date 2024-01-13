package data.elements;

import java.util.Arrays;

public class CarTelemetryData {
    private int speed;
    private float throttle;
    private float steer;
    private float brake;
    private int clutch;
    private int gear;
    private int engineRPM;
    private int drs;
    private int revLightsPercent;
    private int revLightsBitValue;
    private WheelData<Integer> brakeTemperature;
    private WheelData<Integer> tyresSurfaceTemperature;
    private WheelData<Integer> tyresInnerTemperature;
    private int engineTemperature;
    private WheelData<Float> tyresPressure;
    private WheelData<Integer> surfaceType;
    
    public CarTelemetryData() {}

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public float getThrottle() {
        return throttle;
    }
    public void setThrottle(float throttle) {
        this.throttle = throttle;
    }
    public float getSteer() {
        return steer;
    }
    public void setSteer(float steer) {
        this.steer = steer;
    }
    public float getBrake() {
        return brake;
    }
    public void setBrake(float brake) {
        this.brake = brake;
    }
    public int getClutch() {
        return clutch;
    }
    public void setClutch(int clutch) {
        this.clutch = clutch;
    }
    public int getGear() {
        return gear;
    }
    public void setGear(int gear) {
        this.gear = gear;
    }
    public int getEngineRPM() {
        return engineRPM;
    }
    public void setEngineRPM(int engineRPM) {
        this.engineRPM = engineRPM;
    }
    public int getDrs() {
        return drs;
    }
    public void setDrs(int drs) {
        this.drs = drs;
    }
    public int getRevLightsPercent() {
        return revLightsPercent;
    }
    public void setRevLightsPercent(int revLightsPercent) {
        this.revLightsPercent = revLightsPercent;
    }
    public int getRevLightsBitValue() {
        return revLightsBitValue;
    }
    public void setRevLightsBitValue(int revLightsBitValue) {
        this.revLightsBitValue = revLightsBitValue;
    }
    public WheelData<Integer> getBrakeTemperature() {
        return brakeTemperature;
    }
    public void setBrakeTemperature(WheelData<Integer> brakeTemperature) {
        this.brakeTemperature = brakeTemperature;
    }
    public WheelData<Integer> getTyresSurfaceTemperature() {
        return tyresSurfaceTemperature;
    }
    public void setTyresSurfaceTemperature(WheelData<Integer> tyresSurfaceTemperature) {
        this.tyresSurfaceTemperature = tyresSurfaceTemperature;
    }
    public WheelData<Integer> getTyresInnerTemperature() {
        return tyresInnerTemperature;
    }
    public void setTyresInnerTemperature(WheelData<Integer> tyresInnerTemperature) {
        this.tyresInnerTemperature = tyresInnerTemperature;
    }
    public int getEngineTemperature() {
        return engineTemperature;
    }
    public void setEngineTemperature(int engineTemperature) {
        this.engineTemperature = engineTemperature;
    }
    public WheelData<Float> getTyresPressure() {
        return tyresPressure;
    }
    public void setTyresPressure(WheelData<Float> tyresPressure) {
        this.tyresPressure = tyresPressure;
    }
    public WheelData<Integer> getSurfaceType() {
        return surfaceType;
    }
    public void setSurfaceType(WheelData<Integer> surfaceType) {
        this.surfaceType = surfaceType;
    }

    public String toString() {
        return
            "Speed: "                           + speed +
                "\nThrottle: "                  + throttle +
                "\nSteer: "                     + steer +
                "\nBrake: "                     + brake +
                "\nClutch: "                    + clutch +
                "\nGear: "                      + gear +
                "\nEngine RPM: "                + engineRPM +
                "\nDRS: "                       + drs +
                "\nRev Lights Percent: "        + revLightsPercent +
                "\nRev Lights Bit Value: "      + revLightsBitValue +
                "\nBrakes Temperature: "        + brakeTemperature +
                "\nTyres Surface Temperature: " + tyresSurfaceTemperature +
                "\nTyres Inner Temperature: "   + tyresInnerTemperature +
                "\nEngine Temperature: "        + engineTemperature +
                "\nTyres Pressure: "            + tyresPressure +
                "\nSurface Type: "              + surfaceType;
    }

}
