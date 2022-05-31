package com.jeequan.jeepay.components.mq.vender.aliyunrocketmq.receive;

import com.jeequan.jeepay.components.mq.constant.MQVenderCS;
import com.jeequan.jeepay.components.mq.executor.MqThreadExecutor;
import com.jeequan.jeepay.components.mq.model.PayOrderReissueMQ;
import com.jeequan.jeepay.components.mq.vender.aliyunrocketmq.AbstractAliYunRocketMQReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * AliYunRocketMQ消息接收器：仅在vender=AliYunRocketMQ时 && 项目实现IMQReceiver接口时 进行实例化
 * 业务：  支付订单补单（一般用于没有回调的接口，比如微信的条码支付）
 */
@Slf4j
@Component
@ConditionalOnProperty(name = MQVenderCS.YML_VENDER_KEY, havingValue = MQVenderCS.ALIYUN_ROCKET_MQ)
@ConditionalOnBean(PayOrderReissueMQ.IMQReceiver.class)
public class PayOrderReissueAliYunRocketMQReceiver extends AbstractAliYunRocketMQReceiver {

    private static final String CONSUMER_NAME = "支付订单补单消息";

    @Autowired
    private PayOrderReissueMQ.IMQReceiver mqReceiver;

    /**
     * 接收 【 queue 】 类型的消息
     **/
    @Override
    @Async(MqThreadExecutor.EXECUTOR_PAYORDER_MCH_NOTIFY)
    public void receiveMsg(String msg) {
        mqReceiver.receive(PayOrderReissueMQ.parse(msg));
    }

    /**
     * 获取topic名称
     *
     * @return
     */
    @Override
    public String getMQName() {
        return PayOrderReissueMQ.MQ_NAME;
    }

    /**
     * 获取业务名称
     *
     * @return
     */
    @Override
    public String getConsumerName() {
        return CONSUMER_NAME;
    }
}
