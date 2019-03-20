package com.huawei;

import java.util.HashMap;
import java.util.Random;

public class Road {
    private int id;
    private int length;
    private int speed;
    private int channel;
    private int from;
    private int to;
    private boolean isDuplex;
    private HashMap<Integer,Integer> fblocking = new HashMap<>();
    private HashMap<Integer,Integer> tblocking = new HashMap<>();
//    private int weight;
    
    public void addBlocking(int time,int start){
        int key = (time/3)*3;
        if(isDuplex){
            if(start==from){
                int oldValue =  fblocking.getOrDefault(key, 0);
                fblocking.put(key, oldValue+1);
            }else{
                int oldValue =  tblocking.getOrDefault(key, 0);
                tblocking.put(key, oldValue+1);
            }
        }else{
            int oldValue =  fblocking.getOrDefault(key, 0);
            fblocking.put(key, oldValue+1);
        }
        
       
       
    }
    public int getWeigth(){
        return length;
    }
    public double getWeigth(int carSpeed){
        int sp = Math.min(speed,carSpeed);
        return length/sp;
    }
    public double getWeigth(int carSpeed,int time){

//        int sp = Math.min(speed,carSpeed);
        return getWeigth(carSpeed,time,from);
        
    }
    public double getWeigth(int carSpeed,int time,int start){
        int key = (time/3)*3;
        int sp = Math.min(speed,carSpeed);
        int totalNum = length*channel;
        if(isDuplex){
            if(from==start){
//                return length/sp*(totalNum-fblocking.getOrDefault(key, 0))*5;
                return length/sp*5*(tanh((totalNum-fblocking.getOrDefault(key, 0))*1.0/totalNum*1.0));
            }else{
                return length/sp*5*(tanh((totalNum-tblocking.getOrDefault(key, 0))*1.0/totalNum*1.0));
            }
        }

        
//        return  length/sp*(length*channel-tblocking.getOrDefault(key, 0))*5;
        return length/sp*5*(tanh((totalNum-tblocking.getOrDefault(key, 0))*1.0/totalNum*1.0));
    }
//    public static void main(String[] args) {
//        System.out.println(tanh(0.5));
//    }
    private static double sigmoid(double x){
        return 1.0/(1+Math.pow(Math.E,-x));
    }
    private static double tanh(double x){
        return 2.0*sigmoid(2*x)-1;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getChannel() {
        return channel;
    }
    public void setChannel(int channel) {
        this.channel = channel;
    }
    public int getFrom() {
        return from;
    }
    public void setFrom(int from) {
        this.from = from;
    }
    public int getTo() {
        return to;
    }
    public void setTo(int to) {
        this.to = to;
    }
    public boolean getIsDuplex() {
        return isDuplex;
    }
    public void setIsDuplex(boolean isDuplex) {
        this.isDuplex = isDuplex;
    }
  
    
}
