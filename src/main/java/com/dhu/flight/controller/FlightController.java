package com.dhu.flight.controller;

import com.dhu.flight.domain.*;
import com.dhu.flight.service.*;

import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.Errors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/flight",produces = { "application/json;charset=UTF-8"})
public class FlightController {
    @Autowired
    private Account_SERVICE as;
    @Autowired
    private Ticket_SERVICE ts;
    @Autowired
    private Manager_SERVICE ms;


    //User
    //用户登录
    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(@RequestBody Map<String ,String> params, HttpServletRequest request){
        return as.login(params);
    }

    //用户注册
    @PostMapping("/register")
    @ResponseBody
    public Map<String,Object> register(@RequestBody Map<String ,String> params){
        return as.register(params);
    }

    //获取用户信息
    @PostMapping("/getInfo")
    @ResponseBody
    public Map<String,Object> getInfo(@RequestBody Map<String ,String> params){
        return as.getInfo(params);
    }

    @Value("${web.upload-path}")
    private String path;
    //用户上传头像
    @PostMapping("/avatar")
    @ResponseBody
    public Map<String,Object> avatar(@RequestBody Map<String ,String> params){
        return as.avatar(params);
    }

    //用户注销
    @PostMapping("/logout")
    @ResponseBody
    public Map<String,Object>  logout(@RequestBody Map<String ,String> params){
        return as.logout(params);
    }

    //用户修改密码
    @PostMapping("/changePassword")
    @ResponseBody
    public Map<String,Object> changePassword(@RequestBody Map<String,String> params, HttpServletRequest request){
        return as.changePassword(params,request);
    }

    //用户充值
    @PostMapping("/recharge")
    @ResponseBody
    public Map<String,Object> recharge(@RequestBody Map<String,String> params){
        return as.recharge(params);
    }

    //Ticket
    //用户搜索航班
    @PostMapping("/searchflight")
    @ResponseBody
    public Map<String,Object> search(@RequestBody Map<String,String> params){
        return ts.searchFlight(params);
    }

    @PostMapping("/searchflighttwo")
    @ResponseBody
    public Map<String,Object> searchtwo(@RequestBody Map<String,String> params){
        return ts.searchFlighttwo(params);
    }

    //通过航班id返回航班信息
    @PostMapping("/searchflightbyid")
    @ResponseBody
    public Map<String,Object> searchfid(@RequestBody Map<String,String> params){
        return ts.searchFlightById(params);
    }

    //用户购买机票
    @PostMapping("/buyticket")
    @ResponseBody
    public Map<String,Object> buyticket(@RequestBody Map<String,String> params){
        return ts.buyTicket(params);
    }

    //用户查看购买的机票
    @PostMapping("/showticket")
    @ResponseBody
    public Map<String,Object> showticket(@RequestBody Map<String,String> params){
        return ts.findTicketByCId(params);
    }

    //Manager
    //管理员查看航班信息
    @PostMapping("/showflight")
    @ResponseBody
    public Map<String,Object> showflight(@RequestBody Map<String,String> params){
        return ms.selectFlightByPage(params);
    }

    //管理员搜索航班信息
    @PostMapping("/findflight")
    @ResponseBody
    public Map<String,Object> findflight(@RequestBody Map<String,String> params){
        return ms.findFlight(params);
    }

    //管理员查看日志文件
    @PostMapping("/showlog")
    @ResponseBody
    public Map<String,Object> showlog(){
        return ms.findalllog();
    }

    //管理员查看消费日志
    @PostMapping("/showrecord")
    @ResponseBody
    public Map<String,Object> showrecord(){
        return ms.findallrecord();
    }

    //管理员添加航班
    @PostMapping("/addflight")
    @ResponseBody
    public Map<String,Object> addflight(@RequestBody Map<String,String> params){
        return ms.addFlight(params);
    }

    //管理员删除航班
    @PostMapping("/deleteflight")
    @ResponseBody
    public Map<String,Object> deleteflight(@RequestBody Map<String,String> params){
        return ms.delFlight(params);
    }

    //管理员更改航班
    @PostMapping("/editflight")
    @ResponseBody
    public Map<String,Object> editflight(@RequestBody Map<String,String> params){
        return ms.editFlight(params);
    }

    //管理员获得统计数据
    @PostMapping("/gettest")
    @ResponseBody
    public Map<String,Object> gettest(@RequestBody Map<String,String> params){
        Map<String, Object> datas=new HashMap<String, Object>();
        Map<String, Object> l1=new HashMap<String, Object>();
        Map<String, Object> l2=new HashMap<String, Object>();
        int a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11;
        int b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
        a1=ms.selectGo("北京");
        a2=ms.selectGo("上海");
        a3=ms.selectGo("杭州");
        a4=ms.selectGo("广州");
        a5=ms.selectGo("深圳");
        a6=ms.selectGo("重庆");
        a7=ms.selectGo("武汉");
        a8=ms.selectGo("天津");
        a9=ms.selectGo("郑州");
        a10=ms.selectGo("西安");
        a11=ms.selectGo("成都");
        b1=ms.selectReturn("北京");
        b2=ms.selectReturn("上海");
        b3=ms.selectReturn("杭州");
        b4=ms.selectReturn("广州");
        b5=ms.selectReturn("深圳");
        b6=ms.selectReturn("重庆");
        b7=ms.selectReturn("武汉");
        b8=ms.selectReturn("天津");
        b9=ms.selectReturn("郑州");
        b10=ms.selectReturn("西安");
        b11=ms.selectReturn("成都");
        l1.put("a1",a1);l1.put("a2",a2);l1.put("a3",a3);l1.put("a4",a4);l1.put("a5",a5);
        l1.put("a6",a6);l1.put("a7",a7);l1.put("a8",a8);l1.put("a9",a9);l1.put("a10",a10);
        l1.put("a11",a11);
        l2.put("b1",b1);l2.put("b2",b2);l2.put("b3",b3);l2.put("b4",b4);l2.put("b5",b5);
        l2.put("b6",b6);l2.put("b7",b7);l2.put("b8",b8);l2.put("b9",b9);l2.put("b10",b10);
        l2.put("b11",b11);
        datas.put("l1",l1);
        datas.put("l2",l2);
        return datas;
    }
}
