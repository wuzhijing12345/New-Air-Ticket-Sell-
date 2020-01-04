package com.dhu.flight.service.impl;

import com.dhu.flight.domain.*;
import com.dhu.flight.dao.*;
import com.dhu.flight.service.*;
import com.dhu.flight.encrypt_decrypt.RSA;
import com.dhu.flight.utils.FileUtils;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Account_SERVICEImpl implements Account_SERVICE{
    @Autowired
    private DAO accountDao;

    @Override
    public Map<String, Object> login(Map<String ,String> params) {
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, Object> datas = new HashMap<String, Object>();
        User user;
        String name = (String)params.get("username");                //通过账号密码登录
        String password = (String)params.get("password");
        String role = null;
        password = RSA.passwordEncrypt(password);
        user = accountDao.finduser(name);
        if (user == null) {
            datas.put("error", "没有这个用户");
        } else if (!user.getPassword().equals(password)) {
            datas.put("error", "密码不正确");
        } else if (user.getStatus().equals("online")) {
            datas.put("error", "用户已经在线");          //主code:非法token
        } else {
            if (user.getUserId() == 1) {
                role = "admin";
            } else {
                role = "normal";
            }
            accountDao.updateStatusById(user.getUserId(), user.getStatus());           //修改登陆状态
            user.setStatus("online");
            datas.put("role", role);                                      //封装数据，用户角色
            datas.put("data", user);
        }
        return datas;
    }

    @Override
    public Map<String, Object> register(Map<String ,String> params) {

        String name = params.get("username");
        String password = params.get("password");
        String telnumber = params.get("telnumber");
        password = RSA.passwordEncrypt(password);           //对密码进行RSA加密，获取password
        int flag=0;
        Map<String, Object> datas=new HashMap<String, Object>();

        List<User> list=accountDao.getUserinfom();
        for(User user1 :list) {
            if(name.equals(user1.getUsername())){
                flag=1;
            }
        }
        if(flag==1){
            datas.put("error", "不可注册同样的用户名");
        }
        else
        {
            User u = new User();
            u.setUsername(name);
            u.setPassword(password);
            u.setTelnumber(telnumber);
            u.setStatus("offline");
            accountDao.addClient(u);
            datas.put("data", u);
            datas.put("role", "normal");
        }
        return datas;
    }

    @Override
    public Map<String,Object> avatar(Map<String ,String> params){
        Map<String,Object> datas = new HashMap<String, Object>();                             //用于封装返回数据
        String fileName=params.get("picture");
        String id1=params.get("Id");
        Long id=Long.parseLong(id1);
        accountDao.updateAvatarById("E://picture1/"+fileName,id);                      //将用户头像路径保存到数据库中
        datas.put("datas","E://picture1/"+fileName);
        return datas;
    }

    @Override
    public Map<String,Object> logout(Map<String ,String> params){
        String id=params.get("userId");
        long cid= Long.parseLong(id);
        User user;
        user = accountDao.getClient(cid);
        Map<String, Object> datas=new HashMap<String, Object>();
        accountDao.updateStatusById(user.getUserId(),user.getStatus());         //改变账号状态
        datas.put("datas","success");
        return datas;
    }

    @Override
    public Map<String, Object> changePassword(Map<String, String> params, HttpServletRequest request) {

        String oldPassword = params.get("oldPassword");
        oldPassword = RSA.passwordEncrypt(oldPassword);
        String newPassword = params.get("newPassword");
        String id = params.get("userId");

        Map<String,Object> datas = new HashMap<>();
        User user = accountDao.getClient(Long.parseLong(id));
        if(!user.getPassword().equals(oldPassword)){                //验证输入的原密码是否正确，不正确返回空
            datas.put("error","原密码不正确");
            return datas;
        }
        newPassword = RSA.passwordEncrypt(newPassword);            //对密码进行RSA加密，获取password

        accountDao.updatePassAndTokenById(user.getUserId(),newPassword);
        user = accountDao.getClient(user.getUserId());
        datas.put("user",user);
        return datas;
    }

    @Override
    public Map<String,Object> recharge(Map<String ,String> params){
        String id=params.get("userId");
        long cid= Long.parseLong(id);
        String mon=params.get("money");
        double money=Double.parseDouble(mon);
        User user=new User();
        user=accountDao.getClient(cid);
        user.setMoney(money+user.getMoney());
        accountDao.editClient(user);
        Map<String,Object> datas = new HashMap<>();
        user=accountDao.getClient(cid);
        datas.put("user",user);
        return datas;
    }

    @Override
    public Map<String,Object> getInfo(Map<String ,String> params){
        String id = params.get("userId");
        long userId=Long.parseLong(id);
        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> datas=new HashMap<String, Object>();
        User user = new User();
        user = accountDao.getClient(userId);
        datas.put("data",user);
        datas.put("role","normal");
        return datas;
    }

}
