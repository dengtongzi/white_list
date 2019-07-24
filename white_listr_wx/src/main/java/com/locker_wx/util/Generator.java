package com.locker_wx.util;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
* @author 作者 dengtongzi
* @describe 自动生成代码工具类
* @version 创建时间：2019年6月25日 上午11:46:52
*/
public class Generator {

    public static void main(String[] args) {
        String packageName = "com.locker_wx.weixin";
        boolean serviceNameStartWithI = false;
        //auth -> UserService, 设置成true: auth -> IUserService
        generateByTables(serviceNameStartWithI, packageName, "dengtongzi", "ybb", "wx_amount");

        System.out.println("completed...");
    }

    /**
     * @param serviceNameStartWithI
     * @param packageName   包名
     * @param author  作者
     * @param database  数据库名
     * @param tableNames 表名
     */
    private static void generateByTables(boolean serviceNameStartWithI, String packageName, String author, String database, String tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/ybb?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("root")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                .setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .entityTableFieldAnnotationEnable(true)
//              .setSuperMapperClass("cn.saytime.mapper.BaseMapper")
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        
        config.setActiveRecord(false)
                .setAuthor(author)
                .setOutputDir("D:\\eclipse-jee-2019-03-R-win32-x86_64\\work\\locker_wx\\src\\main\\java")
                .setFileOverride(true)
                .setEnableCache(false);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("model")
                                .setMapper("mapper")
                                .setService("service")
                                .setServiceImpl("service.impl")
                                .setXml("mapper.xml")
                ).execute();
    }

}