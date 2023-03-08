package com.flx.manyif;

import org.springframework.stereotype.Service;

@Service
public class TwoCommand implements BaseCommand {
    @Override
    public void process(String msg) {
        System.out.println("two process result:" + msg);
    }
}
