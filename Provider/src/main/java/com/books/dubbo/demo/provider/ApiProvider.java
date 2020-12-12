package com.books.dubbo.demo.provider;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import com.books.dubbo.demo.api.GreetingService;

public class ApiProvider {

	public static void main(String[] args) throws IOException {
		// 1.创建ServiceConfig实例
		ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<GreetingService>();
		// 2.设置应用程序配置
		serviceConfig.setApplication(new ApplicationConfig("first-dubbo-provider"));

		// 3.设置服务注册中心信息
		RegistryConfig registryConfig = new RegistryConfig("zookeeper://192.168.1.103:2181");
		serviceConfig.setRegistry(registryConfig);
//		serviceConfig.setRegistries(Arrays.asList(
//				new RegistryConfig("zookeeper://192.168.1.104:2181"),
//				new RegistryConfig("zookeeper://192.168.1.104:2182")
//		));
		// 4.设置接口与实现类
		serviceConfig.setInterface(GreetingService.class);
		serviceConfig.setRef(new GreetingServiceImpl());

		// 5.设置服务分组与版本 
		serviceConfig.setVersion("1.0.0");
		serviceConfig.setGroup("dubbo");
		// 延迟10分钟发布
		serviceConfig.setDelay(1000);
		// 只导出远程服务，可选值：remote, local，默认值导出到本地
//		serviceConfig.setScope("remote");

		// 6.设置线程池策略，默认是 fixed
//		HashMap<String, String> parameters = new HashMap<>();
//		parameters.put("threadpool", "mythreadpool");
//		serviceConfig.setParameters(parameters);

		// 6.设置线程模型
//		HashMap<String, String> parameters = new HashMap<>();
//		parameters.put("dispatcher", "mydispatcher");
//		serviceConfig.setParameters(parameters);

		// 7.导出服务
		serviceConfig.export();

		// 8.挂起线程，避免服务停止
		System.out.println("server is started");
		System.in.read();
	}

}
