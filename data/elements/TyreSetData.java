package data.elements;

public class TyreSetData {
    private int actualTyreCompound;
    private int visualTyreCompound;
    private int wear;
    private int available;
    private int recommendedSession;
    private int lifeSpan;
    private int usableLife;
    private int lapDeltaTime;
    private int fitted;

    public int getActualTyreCompound() {
        return actualTyreCompound;
    }

    public TyreSetData() {}

    public void setActualTyreCompound(int actualTyreCompound) {
        this.actualTyreCompound = actualTyreCompound;
    }

    public int getVisualTyreCompound() {
        return visualTyreCompound;
    }

    public void setVisualTyreCompound(int visualTyreCompound) {
        this.visualTyreCompound = visualTyreCompound;
    }

    public int getWear() {
        return wear;
    }

    public void setWear(int wear) {
        this.wear = wear;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getRecommendedSession() {
        return recommendedSession;
    }

    public void setRecommendedSession(int recommendedSession) {
        this.recommendedSession = recommendedSession;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(int lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public int getUsableLife() {
        return usableLife;
    }

    public void setUsableLife(int usableLife) {
        this.usableLife = usableLife;
    }

    public int getLapDeltaTime() {
        return lapDeltaTime;
    }

    public void setLapDeltaTime(int lapDeltaTime) {
        this.lapDeltaTime = lapDeltaTime;
    }

    public int getFitted() {
        return fitted;
    }

    public void setFitted(int fitted) {
        this.fitted = fitted;
    }

    public String toString() {
        String data = 
        "Actual Tyre Compound: " + actualTyreCompound
        + "\nVisual Tyre Compound: " + visualTyreCompound
        + "\nWear: " + wear
        + "\nAvailable: " + available
        + "\nRecomended Session: " + recommendedSession
        + "\nLife Span: " + lifeSpan
        + "\nUsable Life: " + usableLife
        + "\nLap Delta Time:" + lapDeltaTime
        + "\nFitted: " + fitted;
        return data;
    }
    
    
}
