package com.scut.backend.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@TableName(value = "chat")
public class Chat {
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private int chatId;
    @TableField("fromName")
    private String fromName;
    @TableField("toName")
    private String toName;
    private long time;
    private String msg;
}
