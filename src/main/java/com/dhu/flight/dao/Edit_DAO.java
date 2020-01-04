package com.dhu.flight.dao;
import com.dhu.flight.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface Edit_DAO extends Alter_DAO, Add_DAO, Del_DAO{

}
