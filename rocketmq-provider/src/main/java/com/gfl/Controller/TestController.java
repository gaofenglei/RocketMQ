package com.gfl.Controller;

import com.gfl.config.OnewayProducer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
public class TestController {

    @RequestMapping("a")
    @ResponseBody
    public String test() throws InterruptedException, RemotingException, MQClientException, UnsupportedEncodingException {
        OnewayProducer.Oneway("please_rename_unique_group_name","localhost:9876","TopicTest","TagC","qazwsxedcrfvtgbyhnujmikolp");
        return "success";
    }

}
