package com.dhu.flight.service.impl;
import com.dhu.flight.domain.*;
import com.dhu.flight.dao.*;
import com.dhu.flight.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.DoubleAccumulator;

@Service
public class Manager_Servicesimpl implements Manager_SERVICE{

    @Autowired
    private DAO dao;

    @Override
    public Map<String,Object> selectFlightByPage(Map<String, String> params){
        Map<String, Object> datas=new HashMap<String, Object>();
        int currentPage=Integer.parseInt(params.get("page"));
        HashMap<String,Object> map=new HashMap<String,Object>();
        Page<Flight> page=new Page<Flight>();
        //封装当前页数
        page.setCurrPage(currentPage);
        //每页显示的数据
        int pageSize=50;
        page.setPageSize(pageSize);
        //封装总记录数
        int totalCount=dao.selectCount();
        page.setTotalCount(totalCount);
        //封装总页数
        double totalcount=totalCount;
        Double num=Math.ceil(totalcount/pageSize);
        page.setTotalPage(num.intValue());//转换为整数
        List<Flight> list=dao.selectFlightByPage((currentPage-1)*pageSize,page.getPageSize());
        page.setList(list);
        datas.put("pagemess",page);
        return datas;
    }

    @Override
    public Map<String,Object> findallflight(){
        Map<String, Object> datas=new HashMap<String, Object>();
        List<Flight> some = dao.findallflight();
        datas.put("flightlist",some);
        return datas;
    }

    @Override
    public Map<String,Object> findFlight(Map<String, String> params){
        String flightId=params.get("flightId");
        Map<String, Object> datas=new HashMap<String, Object>();
        Flight some = new Flight();
        some=dao.findFlight(flightId);
        datas.put("flight",some);
        return datas;
    }

    @Override
    public Map<String,Object> findallrecord(){
        Map<String, Object> datas=new HashMap<String, Object>();
        List<Record> some = dao.getRecord();
        datas.put("recordlist",some);
        return datas;
    }

    @Override
    public Map<String,Object> findalllog(){
        Map<String, Object> datas=new HashMap<String, Object>();
        List<Log> some = dao.getLog();
        datas.put("loglist",some);
        return datas;
    }

    @Override
    public void addLog(int logType,Flight flight){
        Map<String, Object> datas=new HashMap<String, Object>();
        Log newlog = new Log();
        newlog.setLogtype(logType);
        newlog.setFlight(flight);
        dao.addLog(newlog);
    }

    @Override
    public Map<String,Object> addFlight(Map<String, String> params) {
        Map<String, Object> datas=new HashMap<String, Object>();
        Flight flight=new Flight();
        flight.setFlightId(params.get("flightId"));
        flight.setFltype(params.get("flighttype"));
        flight.setStart(params.get("start"));
        flight.setEnd(params.get("end"));
        flight.setStarttime(Integer.valueOf(params.get("starttime")));
        flight.setEndtime(Integer.valueOf(params.get("endtime")));
        flight.setDate(params.get("date"));
        flight.setWaitsite(params.get("waitsite"));
        flight.setLowprice(Double.parseDouble(params.get("lowprice")));
        flight.setTopprice(Double.parseDouble(params.get("topprice")));
        dao.addFlight(flight);
        addLog(2,flight);
        datas.put("code","success");
        return datas;
    }

    @Override
    public Map<String,Object> delFlight(Map<String, String> params){
        Map<String, Object> datas=new HashMap<String, Object>();
        String id=params.get("flightid");
        Flight flight=new Flight();
        flight=dao.findFlight(id);
        addLog(3,flight);
        dao.delTicketManage(id);
        dao.delFlight(id);
        datas.put("code","success");
        return datas;

    }

    @Override
    public Map<String,Object> editFlight(Map<String, String> params){
        Map<String, Object> datas=new HashMap<String, Object>();
        Flight flight=new Flight();
        flight.setFlightId(params.get("flightId"));
        flight.setFltype(params.get("flighttype"));
        flight.setStart(params.get("start"));
        flight.setEnd(params.get("end"));
        flight.setStarttime(Integer.valueOf(params.get("starttime")));
        flight.setEndtime(Integer.valueOf(params.get("endtime")));
        flight.setDate(params.get("date"));
        flight.setWaitsite(params.get("waitsite"));
        flight.setLowprice(Double.parseDouble(params.get("lowprice")));
        flight.setTopprice(Double.parseDouble(params.get("topprice")));
        dao.editFlight(flight);
        addLog(1,flight);
        datas.put("code","success");
        return datas;
    }

    public int selectGo(String end){
        int a=dao.selectGo(end);
        return a;
    }

    public int selectReturn( String start){
        int b=dao.selectReturn(start);
        return b;
    }
}
