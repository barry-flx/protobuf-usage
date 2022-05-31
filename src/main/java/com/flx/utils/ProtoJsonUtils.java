package com.flx.utils;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public class ProtoJsonUtils {

  public static String toJson(Message sourceMessage) throws InvalidProtocolBufferException {
    if (sourceMessage == null) {
      return null;
    }
    return JsonFormat.printer().includingDefaultValueFields().print(sourceMessage);
  }

  public static Message toObject(Message.Builder targetBuilder, String json)
      throws InvalidProtocolBufferException {
    if (json == null) {
      return null;
    }
    //ignoringUnknownFields 如果 json 串中存在的属性 proto 对象中不存在，则进行忽略，否则会抛出异常
    JsonFormat.parser().ignoringUnknownFields().merge(json, targetBuilder);
    return targetBuilder.build();
  }

}
