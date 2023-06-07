package com.scut.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scut.backend.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>  {

}
