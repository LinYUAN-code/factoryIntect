package com.scut.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scut.backend.bean.Chat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper extends BaseMapper<Chat> {
}
