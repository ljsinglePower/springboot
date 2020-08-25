package com.hospital.lyy.moudle.login.children.authority.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lj
 * @since 2020-08-23
 */
public class Authority extends Model<Authority> {

    private static final long serialVersionUID=1L;

    @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;

    private String authority;

    @TableField("authorityDesc")
    private String authorityDesc;

    private Integer rid;


    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Override
    protected Serializable pkVal() {
        return this.aid;
    }

    @Override
    public String toString() {
        return "Authority{" +
        "aid=" + aid +
        ", authority=" + authority +
        ", authorityDesc=" + authorityDesc +
        ", rid=" + rid +
        "}";
    }
}
