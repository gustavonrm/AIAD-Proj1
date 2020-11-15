package com.aiad2021;


public class AuctionInfo {

    private final String type;
    private final double basePrice;
    private double winningPrice;
    private final String ip;
    private final double minBid;
    private double currentBid;
    private int movement;

    public AuctionInfo(String type, double basePrice, double minBid, double winningPrice, String ip) {
        this.type = type;
        this.basePrice = basePrice;
        this.minBid = minBid;
        this.winningPrice = winningPrice;
        this.ip = ip;
        this.currentBid = 0;
        this.movement = 0;
    }

    public String getType() {
        return type;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setWinningPrice(double winningPrice) {
        this.winningPrice = winningPrice;
    }

    public double getWinningPrice() {
        return winningPrice;
    }

    public String getIp() {
        return ip;
    }

    public double getMinBid() {
        return minBid;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }
}
