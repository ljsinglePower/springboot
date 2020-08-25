package com.hospital.lyy.moudle.login.children.roles.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lj
 * @since 2020-08-23
 */
public class Roles extends Model<Roles> {

    private static final long serialVersionUID=1L;

    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    private String rname;

    private Integer uid;


    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    protected Serializable pkVal() {
        return this.rid;
    }

    @Override
    public String toString() {
        return "Roles{" +
        "rid=" + rid +
        ", rname=" + rname +
        ", uid=" + uid +
        "}";
    }
}
