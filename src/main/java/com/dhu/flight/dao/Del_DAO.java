package com.dhu.flight.dao;

import com.dhu.flight.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.NestingKind;
import java.util.List;


@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface Del_DAO{
    void delFlight(String flightId);
    void delTicket(Purchase purchase);//1.by flight id and client id; 2.by flight id
    void delLog(Log log);
    void delTicketManage(String pid);
}

