package com.liumulin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liuqiang
 * @Description TODO
 * @Date 2020/12/29 0029
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private String username;

    private String password;

    private String salt;
}
