package com.flx.abstracts;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ReqBean implements Serializable {

    private int reqNum;

}
