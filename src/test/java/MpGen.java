import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class MpGen {
    @Test
    public void testGenerator() {
        //全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)//是否支持AR模式
                .setAuthor("lj") //作者
                //.setOutputDir("D:\\workspace_my\\mp03\\src\\main\\java")//生成路径
                .setOutputDir("F:\\idea_2020\\helloSpringboot\\src\\main\\java")//生成路径
                .setFileOverride(true)//文件覆盖
                .setServiceName("%sService") //设置生成的service接口名 首字母是否为I
                .setIdType(IdType.AUTO) //主键策略
        ;
        //数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://localhost:3306/hospital")
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("1234");
        //策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) // 全局大写命名
                //.setDbColumnUnderline(true) //表名 字段名 是否使用下滑线命名
                .setNaming(NamingStrategy.underline_to_camel)//数据库表映射到实体命名策略
                .setInclude("roles");//生成的表
                //.setTablePrefix("tbl_"); // 表前缀
        //包名策略
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.hospital.lyy.moudle.login.children.roles")
                .setController("controller")
                .setEntity("beans")
                .setService("service");
        AutoGenerator ag = new AutoGenerator();
              ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        ag.execute();
    }

}
