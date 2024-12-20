/**
 * @Time: 2024/12/20 16:43
 * @Author: guoxun
 * @File: TbAuthAuthorities
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
@Entity
@Table(name = "tb_auth_authorities")
public class TbAuthAuthorities {

    /**
     * 主键
     */
    @Id
    @TableId(type = IdType.AUTO)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int8 AUTO_INCREMENT")
    private Long id;

    /**
     * 权限名称
     */
    @Column(name = "name", columnDefinition = "VARCHAR(256)")
    private String name;
}
