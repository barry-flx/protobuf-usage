package com.flx.abstracts;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RespBean implements Serializable {

    private int respNum;
}
