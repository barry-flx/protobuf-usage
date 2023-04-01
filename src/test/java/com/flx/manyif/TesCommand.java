package com.flx.manyif;

import com.flx.UsageApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsageApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TesCommand {

    @Autowired
    private CommandContext commandContext;

    @Test
    public void test() {
        BaseCommand baseCommand = commandContext.getInstance("two");
        baseCommand.process("hello");
    }

}
