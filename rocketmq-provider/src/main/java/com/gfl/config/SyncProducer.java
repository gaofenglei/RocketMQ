package com.gfl.config;


import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SyncProducer {

    /*
    1、Producer端发送同步消息
    这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
    */

    public static void main(String[] args) throws Exception  {
        //声明并初始化一个producer
        //需要一个producer group名字作为构造方法的参数，这里为producer1
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        //设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
        //NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
        producer.setNamesrvAddr(RocketMQConfig.NAME_SERVER);
        //调用start()方法启动一个producer实例
        producer.start();
        for (int i = 0; i < 10; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message(RocketMQConfig.TOPIC /* Topic */,
                    "TagA" /* Tag */,
                    ("今天:2021.1." + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //调用producer的send()方法发送消息
            //这里调用的是同步的方式，所以会有返回结果
            SendResult sendResult = producer.send(msg);
            //打印返回结果，可以看到消息发送的状态以及一些相关信息
            System.out.printf("%s%n", sendResult);
        }
        //发送完消息之后，调用shutdown()方法关闭producer
        producer.shutdown();
    }


}
