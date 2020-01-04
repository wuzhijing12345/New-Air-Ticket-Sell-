package com.dhu.flight.service;

import com.dhu.flight.domain.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface Account_SERVICE {
    Map<String,Object> changePassword(Map<String, String> params, HttpServletRequest request);
    Map<String,Object> login(Map<String ,String> params);
    Map<String,Object> register(Map<String ,String> params);
    Map<String,Object> getInfo(Map<String ,String> params);
    Map<String,Object> avatar(Map<String ,String> params);
    Map<String,Object> logout(Map<String ,String> params);
    Map<String,Object> recharge(Map<String ,String> params);
}
