package com.huawei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Road implements Comparable<Road> {
    private int id;
    private int length;
    private int speed;
    private int channel;
    private int from;
    private int to;
    private boolean isDuplex;

    private List<Channel> fchannels = new ArrayList<>();// forward
    private List<Channel> bchannels = new ArrayList<>();// backward

    public boolean block = false;

    private int fcurFirstChannelId = 0;
    private int bcurFirstChannelId = 0;

    public boolean flag = false;
    List<Car> fcarSeq = new ArrayList<>();
    List<Car> bcarSeq = new ArrayList<>();
    public void init() {
        for (int i = 0; i < channel; i++) {
            fchannels.add(new Channel(this, i));
        }
        if (isDuplex) {
            for (int i = 0; i < channel; i++) {
                bchannels.add(new Channel(this, i));
            }
        }

    }

    public int getMaxCarNum() {
        return this.channel * this.length;
    }

    public int getCurHaveCarNum(int start) {
        int cnt = 0;
        if (start == from) {
            for (Channel x : fchannels) {
                cnt += x.channel.size();
            }
        } else {
            for (Channel x : bchannels) {
                cnt += x.channel.size();
            }
        }
        return cnt;
    }

    // 取第一优先级的
    public Car getFirst(int start) {
        if(start==this.to){
            if(!fcarSeq.isEmpty()){
                return fcarSeq.get(0);
            }
            return null;
        }else{
            if(!bcarSeq.isEmpty()){
                return bcarSeq.get(0);
            }
            return null;
        }
    }
    public void createCarSequeue(int start){
        List<Car> carSeq;
        List<Channel> chs;
        Car tmpCar;
       if(start==this.to){
           chs=fchannels;
           carSeq = fcarSeq;
       }else{
           chs=bchannels;
           carSeq = bcarSeq;
       }
       carSeq.clear();
       for(Channel ch :chs){
           if(!ch.channel.isEmpty()){
               tmpCar = ch.channel.getFirst();
               tmpCar.setChannel(ch);
               if(tmpCar.getFlag()==Car.WAIT){
                   carSeq.add(tmpCar);
               }
           }
       }
       Car firstCar=null;
       if(!carSeq.isEmpty())
           firstCar = carSeq.get(0);
       if(carSeq.size()>=2){
         //排序
           carSeq.sort((Car c1,Car c2)->{
               if(c1.isProiority()&&!c2.isProiority()){
                   return -1; 
               }else if(c1.isProiority()&&c2.isProiority()){
                   if(c1.getCurRoadDis()>c2.getCurRoadDis())return -1;
                   else if(c1.getCurRoadDis()<c2.getCurRoadDis()) return 1;
                   else if(c1.getChannel().cid>c2.getChannel().cid) return 1;
                   else if(c1.getChannel().cid<c2.getChannel().cid) return -1;
                   else
                   return 0;
               }else if(!c1.isProiority()&&!c2.isProiority()){
                   if(c1.getCurRoadDis()>c2.getCurRoadDis())return -1;
                   else if(c1.getCurRoadDis()<c2.getCurRoadDis()) return 1;
                   else if(c1.getChannel().cid>c2.getChannel().cid) return 1;
                   else if(c1.getChannel().cid<c2.getChannel().cid) return -1;
                   else
                   return 0;
               }else{
                   return 1;
               }
           });
           //waiting
           
           for(int i=1;i<carSeq.size();i++){
               carSeq.get(i).waiting=firstCar;
           }
       }
      
      
    }
    public Channel getIntoChannels(int start) {
        Car c;
        Channel channel = null;
        if (start == this.from) {
            for (Channel ch : fchannels) {
                if (ch.channel.isEmpty())
                    return ch;
                c = ch.channel.getLast();
                if (c.getFlag() == Car.WAIT) {
                    return ch;
                }
                if (c.getCurRoadDis() > 1) {
                    channel = ch;
                    return channel;
                }
            }
        } else {
            for (Channel ch : bchannels) {
                if (ch.channel.isEmpty())
                    return ch;
                c = ch.channel.getLast();
                if (c.getFlag() == Car.WAIT) {
                    return ch;
                }
                if (c.getCurRoadDis() > 1) {
                    channel = ch;
                    return channel;
                }
            }
        }

        return channel;
    }


    public List<Channel> getFchannels() {
        return fchannels;
    }

    public void setFchannels(List<Channel> fchannels) {
        this.fchannels = fchannels;
    }

    public List<Channel> getBchannels() {
        return bchannels;
    }

    public void setBchannels(List<Channel> bchannels) {
        this.bchannels = bchannels;
    }

    public double getWeigth() {
        return length;
    }

    public double getWeigth(int start) {
        return length;
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
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
       return Integer.hashCode(id);
    }
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(!(obj instanceof Road))
            throw  new ClassCastException("类型不匹配");  
        Road r = (Road)obj;
        return this.id==r.getId();
    }
    @Override
    public int compareTo(Road o) {
        // TODO Auto-generated method stub
        return id - o.id;
    }

}
