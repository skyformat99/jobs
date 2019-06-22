package com.baomidou.jobs.executor.service.jobhandler;

import com.baomidou.jobs.core.biz.model.ReturnT;
import com.baomidou.jobs.core.handler.IJobHandler;
import com.baomidou.jobs.core.handler.annotation.JobHandler;
import com.baomidou.jobs.core.log.XxlJobLogger;
import com.baomidou.jobs.core.util.ShardingUtil;
import org.springframework.stereotype.Service;

/**
 * 分片广播任务
 *
 * @author xuxueli 2017-07-25 20:56:50
 */
@JobHandler(value="shardingJobHandler")
@Service
public class ShardingJobHandler extends IJobHandler {

	@Override
	public ReturnT<String> execute(String param) throws Exception {

		// 分片参数
		ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
		XxlJobLogger.log("分片参数：当前分片序号 = {}, 总分片数 = {}", shardingVO.getIndex(), shardingVO.getTotal());

		// 业务逻辑
		for (int i = 0; i < shardingVO.getTotal(); i++) {
			if (i == shardingVO.getIndex()) {
				XxlJobLogger.log("第 {} 片, 命中分片开始处理", i);
			} else {
				XxlJobLogger.log("第 {} 片, 忽略", i);
			}
		}

		return SUCCESS;
	}

}
