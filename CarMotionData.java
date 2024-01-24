

public class CarMotionData {
    private float worldPositionX;
    private float worldPositionY;
    private float worldPositionZ;
    private float worldVelocityX;
    private float worldVelocityY;
    private float worldVelocityZ;
    private int worldForwardDirX;
    private int worldForwardDirY;
    private int worldForwardDirZ;
    private int worldRightDirX;
    private int worldRightDirY;
    private int worldRightDirZ;
    private float gForceLateral;
    private float gForceLongitudinal;
    private float gForceVertical;
    private float yaw;
    private float pitch;
    private float roll;

    public CarMotionData() {}

    public float getWorldPositionX() {
        return worldPositionX;
    }
    public void setWorldPositionX(float worldPositionX) {
        this.worldPositionX = worldPositionX;
    }
    public float getWorldPositionY() {
        return worldPositionY;
    }
    public void setWorldPositionY(float worldPositionY) {
        this.worldPositionY = worldPositionY;
    }
    public float getWorldPositionZ() {
        return worldPositionZ;
    }
    public void setWorldPositionZ(float worldPositionZ) {
        this.worldPositionZ = worldPositionZ;
    }
    public float getWorldVelocityX() {
        return worldVelocityX;
    }
    public void setWorldVelocityX(float worldVelocityX) {
        this.worldVelocityX = worldVelocityX;
    }
    public float getWorldVelocityY() {
        return worldVelocityY;
    }
    public void setWorldVelocityY(float worldVelocityY) {
        this.worldVelocityY = worldVelocityY;
    }
    public float getWorldVelocityZ() {
        return worldVelocityZ;
    }
    public void setWorldVelocityZ(float worldVelocityZ) {
        this.worldVelocityZ = worldVelocityZ;
    }
    public int getWorldForwardDirX() {
        return worldForwardDirX;
    }
    public void setWorldForwardDirX(int worldForwardDirX) {
        this.worldForwardDirX = worldForwardDirX;
    }
    public int getWorldForwardDirY() {
        return worldForwardDirY;
    }
    public void setWorldForwardDirY(int worldForwardDirY) {
        this.worldForwardDirY = worldForwardDirY;
    }
    public int getWorldForwardDirZ() {
        return worldForwardDirZ;
    }
    public void setWorldForwardDirZ(int worldForwardDirZ) {
        this.worldForwardDirZ = worldForwardDirZ;
    }
    public int getWorldRightDirX() {
        return worldRightDirX;
    }
    public void setWorldRightDirX(int worldRightDirX) {
        this.worldRightDirX = worldRightDirX;
    }
    public int getWorldRightDirY() {
        return worldRightDirY;
    }
    public void setWorldRightDirY(int worldRightDirY) {
        this.worldRightDirY = worldRightDirY;
    }
    public int getWorldRightDirZ() {
        return worldRightDirZ;
    }
    public void setWorldRightDirZ(int worldRightDirZ) {
        this.worldRightDirZ = worldRightDirZ;
    }
    public float getgForceLateral() {
        return gForceLateral;
    }
    public void setgForceLateral(float gForceLateral) {
        this.gForceLateral = gForceLateral;
    }
    public float getgForceLongitudinal() {
        return gForceLongitudinal;
    }
    public void setgForceLongitudinal(float gForceLongitudinal) {
        this.gForceLongitudinal = gForceLongitudinal;
    }
    public float getgForceVertical() {
        return gForceVertical;
    }
    public void setgForceVertical(float gForceVertical) {
        this.gForceVertical = gForceVertical;
    }
    public float getYaw() {
        return yaw;
    }
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }
    public float getPitch() {
        return pitch;
    }
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
    public float getRoll() {
        return roll;
    }
    public void setRoll(float roll) {
        this.roll = roll;
    }

    public String toString() {
        return
        "\nWorldPositionX     : "   + worldPositionX +
        "\nWorldPositionY     : "   + worldPositionY +
        "\nWorldPositionZ     : "   + worldPositionZ +
        "\nWorldVelocityX     : "   + worldVelocityX +
        "\nWorldVelocityY     : "   + worldVelocityY +
        "\nWorldVelocityZ     : "   + worldVelocityZ +
        "\nWorldForwardDirX   : "   + worldForwardDirX +
        "\nWorldForwardDirY   : "   + worldForwardDirY +
        "\nWorldForwardDirZ   : "   + worldForwardDirZ +
        "\nWorldRightDirX     : "   + worldRightDirX +
        "\nWorldRightDirY     : "   + worldRightDirY +
        "\nWorldRightDirZ     : "   + worldRightDirZ +
        "\ngForceLateral      : "   + gForceLateral +
        "\ngForceLongitudinal : "   + gForceLongitudinal +
        "\ngForceVertical     : "   + gForceVertical +
        "\nYaw                : "   + yaw +
        "\nPitch              : "   + pitch +
        "\nRoll               : "   + roll;
    }
}