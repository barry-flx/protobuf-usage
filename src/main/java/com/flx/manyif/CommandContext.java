package com.flx.manyif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
public class CommandContext {

    private ApplicationContext applicationContext;

    @Autowired
    public CommandContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public BaseCommand getInstance(String command) {
        Map<String, String> allClazz = CommandEnums.getAllClazz();
        String clazz = allClazz.get(command);
        BaseCommand baseCommand = null;
        try {
            if (StringUtils.isEmpty(clazz)) {
                clazz = null;
            }
            baseCommand = (BaseCommand) applicationContext.getBean(Class.forName(clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseCommand;
    }
}
