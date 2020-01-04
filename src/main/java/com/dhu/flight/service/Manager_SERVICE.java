package com.dhu.flight.service;
import com.dhu.flight.domain.*;
import java.util.Map;

public interface Manager_SERVICE {
    Map<String,Object> addFlight(Map<String, String> params);
    Map<String,Object> editFlight(Map<String, String> params);
    void addLog(int logType,Flight flight);
    Map<String,Object> findallflight();
    Map<String,Object> findFlight(Map<String, String> params);
    Map<String,Object> delFlight(Map<String, String> params);
    Map<String,Object> findallrecord();
    Map<String,Object> findalllog();
    Map<String,Object> selectFlightByPage(Map<String, String> params);
    int selectGo(String end);
    int selectReturn( String start);
}
