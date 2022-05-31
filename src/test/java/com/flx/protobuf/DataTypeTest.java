package com.flx.protobuf;

import com.flx.dto.DataType;
import com.flx.protobuf.dto.Course;
import com.flx.protobuf.dto.DataTypeProto;
import com.flx.utils.ProtoJsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.TextFormat;
import java.util.Arrays;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

public class DataTypeTest {

  @Test
  public void testDataType() throws InvalidProtocolBufferException {
    DataTypeProto.DataType.Builder builder = DataTypeProto.DataType.newBuilder();
    builder.setId(1)
        .setParentId(0)
        .setName("你好")
        .build();

    // 内部对象
    DataTypeProto.MapValueObject.Builder mapBuilder = DataTypeProto.MapValueObject.newBuilder();
    mapBuilder.setId(2)
        .setName("对象").build();

    // String list
    builder.addStrList("001");
    builder.addStrList("002");
    builder.addStrList("003");

    // object list
    Course course01 = Course.newBuilder().setOrderNo("123").setScore(1).build();
    Course course02 = Course.newBuilder().setOrderNo("456").setScore(2).build();
    builder.addCourseList(course01);
    builder.addCourseList(course02);

    // map
    DataTypeProto.MapValueObject.Builder mapObject01 = DataTypeProto.MapValueObject.newBuilder();
    mapObject01.setId(3)
        .setName("对象3");
    DataTypeProto.MapValueObject.Builder mapObject02 = DataTypeProto.MapValueObject.newBuilder();
    mapObject02.setId(4)
        .setName("对象4");
    builder.putMapObject("mapObject01", mapObject01.build());
    builder.putMapObject("mapObject02", mapObject02.build());

    // 序列化
    DataTypeProto.DataType dataType = builder.build();
    byte[] bytes = dataType.toByteArray();
    System.out.println("protobuf数据bytes[]:" + Arrays.toString(bytes));
    System.out.println("protobuf序列化大小: " + bytes.length);

    // 反序列化
    DataTypeProto.DataType dataType1 = DataTypeProto.DataType.parseFrom(bytes);
    System.out.println("反序列化：" + dataType1.toString());
    // 中文反序列化时会转成八进制，可采用 TextFormat.printToUnicodeString 进行转换
    System.out.println("中文反序列化：" + printToUnicodeString(dataType1));

    // json
    DataTypeProto.DataType dataType2 = DataTypeProto.DataType.parseFrom(bytes);
    String jsonStr = ProtoJsonUtils.toJson(dataType2);
    System.out.println("json格式化结果:" + jsonStr);
    System.out.println("json格式化数据大小:" + jsonStr.getBytes().length);

    Message message = ProtoJsonUtils.toObject(DataTypeProto.DataType.newBuilder(), jsonStr);
    System.out.println("json转protobuf对象：" + printToUnicodeString(message));

    // java object 只能复制基础的属性（浅拷贝），内部对象等不可以复制
    DataType dataType3 = new DataType();
    BeanUtils.copyProperties(dataType2, dataType3);
    System.out.println("复制后对象：" + dataType3.toString());

    // proto json 转 java object
    Gson gson = new GsonBuilder().serializeNulls().create();
    DataType dataType4 = gson.fromJson(jsonStr, DataType.class);
    System.out.println("json转换之后对象：" + dataType4.toString());
  }

  public static String printToUnicodeString(MessageOrBuilder message) {
    return TextFormat.printer().escapingNonAscii(false).printToString(message);
  }

}
