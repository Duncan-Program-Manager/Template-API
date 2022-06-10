package com.dpm.templateapi.rabbitmq;

import com.dpm.templateapi.datamodel.Template;
import com.dpm.templateapi.repository.TemplateRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userReciever implements MessageListener {

    @Autowired
    private TemplateRepository repository;

    public void onMessage(Message message) {
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(new String(message.getBody()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        if(json.get("method").equals("deleteUser"))
        {
            JSONObject userInfo = (JSONObject) json.get("data");
            List<Template> templates = repository.findAllByUsername(userInfo.get("username").toString());
            repository.deleteAll(templates);
        }

    }
}
