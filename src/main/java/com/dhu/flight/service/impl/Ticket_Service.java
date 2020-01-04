package com.dhu.flight.service.impl;

import com.dhu.flight.domain.*;
import com.dhu.flight.dao.*;
import com.dhu.flight.service.*;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Ticket_Service implements Ticket_SERVICE{
    @Autowired
    private DAO dao;

    public Map<String,Object> searchClientById(Map<String, String> params){
        Map<String, Object> datas=new HashMap<String, Object>();
        String userId=params.get("userId");
        long id=Long.parseLong(userId);
        if(id==0){return null;}
        User user=dao.getClient(id);
        datas.put("user",user);
        return datas;
    }

    @Override
    public Map<String,Object> searchFlight(Map<String, String> params){//仅考虑单程,默认输入信息完全
        Map<String, Object> datas=new HashMap<String, Object>();
        String start=params.get("start");
        String end=params.get("end");
        String date=params.get("date");
        List<Flight> l;
        l=dao.getFlight(start,end,date);
        datas.put("goflightlist",l);
        return datas;
    }

    @Override
    public Map<String,Object> searchFlighttwo(Map<String, String> params){//仅考虑单程,默认输入信息完全
        Map<String, Object> datas=new HashMap<String, Object>();
        String start=params.get("start");
        String end=params.get("end");
        String godate=params.get("godate");
        String returndate=params.get("returndate");
        List<Flight> l1,l2;
        l1=dao.getFlight(start,end,godate);
        l2=dao.getFlight(end,start,returndate);
        datas.put("goflightlist",l1);
        datas.put("returnflightlist",l2);
        return datas;
    }

    @Override
    public Map<String,Object> searchFlightById(Map<String, String> params){//默认fid!=null,e=0
        Map<String, Object> datas=new HashMap<String, Object>();
        String fid=params.get("flightId");
        Flight f=dao.getFlightByFId(fid);
        datas.put("flight",f);
        return datas;
    }

    @Override
    public Map<String,Object> buyTicket(Map<String, String> params){//默认fid均不为null,c为舱位，返回值表示买票状态
        Map<String, Object> datas=new HashMap<String, Object>();
        long cid=Long.parseLong(params.get("userId"));
        String fid=params.get("flightId");
        char c[]=params.get("seattype").toCharArray();
        if(cid==0){//未登录
            datas.put("error", "用户未登录");
        }
        User user=dao.getClient(cid);
        Flight flight=dao.getFlightByFId(fid);
        Purchase ticket=new Purchase();
        Record record=new Record();
        double m=user.getMoney();
        double t=flight.getTopprice();
        double l=flight.getLowprice();
        if((c[0]=='T'&&user.getMoney()<t)||(c[0]=='J'&&user.getMoney()<l)){//余额不足，充值
            datas.put("error", "用户余额不足");
        }
        if(c[0]=='T'&&dao.countTickets(fid,c[0])>=10){//头等舱缺票。机型座位数对应关系！
            datas.put("error", "头等舱缺票");
        }
        if(c[0]=='J'&&dao.countTickets(fid,c[0])>=10){//经济舱缺票
            datas.put("error", "经济舱缺票");
        }
        if(c[0]=='T'){
            user.setMoney(m-t);
        }
        else {
            user.setMoney(m-l);
        }
        dao.editClient(user);
        ticket.setPuruserId(cid);
        ticket.setPurflightId(fid);
        ticket.setSeattype(c[0]);
        record.setReflightId(fid);
        record.setReuserId(user.getUserId());
        record.setRetype(1);
        Purchase pur = dao.getTicketByID(user.getUserId(),fid);
        if(pur != null){
            datas.put("error", "不可重复购买");
        }
        else
        {
            dao.addTicket(ticket);
            dao.addRecord(record);
        }
        datas.put("code", "一切正常");//正常
        return datas;
    }

    public Map<String,Object> findTicketByCId(Map<String, String> params){
        Map<String, Object> datas=new HashMap<String, Object>();
        long cid=Long.parseLong(params.get("userId"));
        List<Purchase> p;
        List<Flight> f=new ArrayList<>();
        p=dao.getTicketByCID(cid);
        for(Purchase pur : p){
            String flightId=pur.getPurflightId();
            Flight fli=new Flight();
            fli=dao.findFlight(flightId);
            if(pur.getSeattype()=='T') {
                fli.setType(1);
            }
            else {
                fli.setType(2);
            }
            f.add(fli);
        }
        datas.put("ticketlist",f);
        return datas;
    }

}
