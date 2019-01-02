package com.fanfq.sbt;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fanfq.common.util.task.AsyncTask;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTaskTest {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AsyncTask task;

	@Test
	public void testSyncTasks() throws Exception {
		long start = System.currentTimeMillis();
		Future<String> task1 = task.doTask1();
		Future<String> task2 = task.doTask2();
		
		// 2个任务都调用完成，退出循环等待
        while (!task1.isDone() || !task2.isDone()) {
            Thread.sleep(1000);
        }
        
        System.out.println(task1.get());
        System.out.println(task2.get());

        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");

	}
	
	@Test
	public void AsyncTaskTest() throws InterruptedException, ExecutionException {
		Future<String> task1 = task.doTask1();
		Future<String> task2 = task.doTask2();
		
		while(true) {
			if(task1.isDone() && task2.isDone()) {
				logger.info("Task1 result: {}", task1.get());
				logger.info("Task2 result: {}", task2.get());
				break;
			}
			Thread.sleep(1000);
		}
		
		logger.info("All tasks finished.");
	}
}
