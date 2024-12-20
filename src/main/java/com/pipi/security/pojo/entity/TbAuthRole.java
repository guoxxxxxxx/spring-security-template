/**
 * @Time: 2024/12/20 16:33
 * @Author: guoxun
 * @File: TbAuthRole
 * @Description:
 */

package com.pipi.security.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "tb_auth_role")
@Entity
public class TbAuthRole {

    /**
     * 主键id
     */
    @Id
    @TableId(type = IdType.AUTO)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int8 AUTO_INCREMENT")
    private Long id;

    /**
     * 用户角色名称
     */
    @Column(name = "name")
    private String name;
}
