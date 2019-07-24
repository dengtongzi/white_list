package com.locker_wx.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

/**
* @author 作者 dengtongzi
* @describe
* @version 创建时间：2019年6月25日 下午4:49:07
*/
//Spring boot方式
@EnableTransactionManagement
@Configuration
@MapperScan("com.locker_wx.weixin.mapper")
@ComponentScan("com.locker_wx.weixin.service")
@EntityScan("com.locker_wx.weixin.model")
public class MybatisPlusConfig {

  /**
   * 分页插件
   */
  @Bean(name = "pagination")
  public PaginationInterceptor paginationInterceptor() {
      PaginationInterceptor page = new PaginationInterceptor();
      page.setDialectType("mysql");
      return page;
  }
}
