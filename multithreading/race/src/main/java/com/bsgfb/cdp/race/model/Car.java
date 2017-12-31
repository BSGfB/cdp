package com.bsgfb.cdp.race.model;

/**
 * Represents car, where bestLapTime show haw fast pilot can finish one lap
 */
public class Car {
    private String pilotName;
    private Long bestLapTime;

    public Car(final String pilotName, final Long bestLapTime) {
        this.pilotName = pilotName;
        this.bestLapTime = bestLapTime;
    }

    public String getPilotName() {
        return pilotName;
    }

    public Long getBestLapTime() {
        return bestLapTime;
    }

    @Override
    public String toString() {
        return "Car{" +
                "pilotName='" + pilotName + '\'' +
                ", bestLapTime=" + bestLapTime +
                '}';
    }
}
