package com.flx.manyif;

import org.springframework.stereotype.Service;

@Service
public class OneCommand implements BaseCommand {
    @Override
    public void process(String msg) {
        System.out.println("one process result:" + msg);
    }
}
