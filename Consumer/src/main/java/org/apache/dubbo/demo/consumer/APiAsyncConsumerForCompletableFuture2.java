package org.apache.dubbo.demo.consumer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import com.books.dubbo.demo.api.GreetingService;

public class APiAsyncConsumerForCompletableFuture2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 1.创建服务引用对象，并设置数据
		ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<GreetingService>();
		referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
		referenceConfig.setRegistry(new RegistryConfig("zookeeper://192.168.1.103:2181"));
		referenceConfig.setInterface(GreetingService.class);
		referenceConfig.setTimeout(30000);
		referenceConfig.setVersion("1.0.0");
		referenceConfig.setGroup("dubbo");

		// 2. 设置为异步
		referenceConfig.setAsync(true);

		// 3. 直接返回null
		GreetingService greetingService = referenceConfig.get();
		System.out.println(greetingService.sayHello("world1"));

		// 4.异步执行回调
		CompletableFuture<String> future1 = RpcContext.getContext().getCompletableFuture();
//		future1.whenComplete((v, e) -> {
//			if (e != null) {
//				e.printStackTrace();
//			} else {
//				System.out.println(v);
//			}
//		});

		// 3. 直接返回null
		greetingService = referenceConfig.get();
		System.out.println(greetingService.sayHello("world2"));

		// 4.异步执行回调
		CompletableFuture<String> future2 = RpcContext.getContext().getCompletableFuture();
//		future2.whenComplete((v, e) -> {
//			if (e != null) {
//				e.printStackTrace();
//			} else {
//				System.out.println(v);
//			}
//		});

		String result1 = future1.get();
		System.out.println(result1);

		String result2 = future2.get();
		System.out.println(result2);

		System.out.println("over");
		Thread.currentThread().join();
	}

}