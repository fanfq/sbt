package com.fanfq.common.util.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Timer {

	protected final Logger logger = LoggerFactory.getLogger(Timer.class);

	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟执行一次
	public void statusCheck() {
		logger.info("每分钟执行一次。开始……");
	}

	@Scheduled(fixedRate = 10000)// 每10s钟执行一次
	public void testTasks() {
		logger.info("每10秒执行一次。开始……");
	}
}
