package com.flx.manyif;

import org.springframework.stereotype.Service;

@Service
public class ThreeCommand implements BaseCommand {
    @Override
    public void process(String msg) {
        System.out.println("three process result:" + msg);
    }
}
