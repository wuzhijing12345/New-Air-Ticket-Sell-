package com.dhu.flight.dao;

import com.dhu.flight.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface Alter_DAO{
    void editClient(User user);//user function
    void editFlight(Flight flight);//manager function
    void editTicket(Purchase purchase);
    void updateStatusById(@Param("userId")long id, @Param("status") String status);
    void updateTokenById(@Param("userId") long id, @Param("token") String token);
    void updateAvatarById(@Param("avatarPath")String avatarPath, @Param("userId") long id);
    void updatePassAndTokenById(@Param("userId") long id, @Param("password") String password);


}

