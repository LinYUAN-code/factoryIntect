package com.scut.backend.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName(value = "user")
@AllArgsConstructor
public class User {

    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private int userId;
    private String name;
    private String token;
}
