// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Course.proto

package com.flx.protobuf.dto;

public final class CourseProto {
  private CourseProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Course_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Course_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Course_Model_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Course_Model_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014Course.proto\"T\n\006Course\022\020\n\010order_no\030\001 \001" +
      "(\t\022\r\n\005score\030\002 \001(\r\032)\n\005Model\022\017\n\007amt_qty\030\003 " +
      "\001(\004\022\017\n\007amt_amt\030\004 \001(\004B%\n\024com.flx.protobuf" +
      ".dtoB\013CourseProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Course_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Course_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Course_descriptor,
        new java.lang.String[] { "OrderNo", "Score", });
    internal_static_Course_Model_descriptor =
      internal_static_Course_descriptor.getNestedTypes().get(0);
    internal_static_Course_Model_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Course_Model_descriptor,
        new java.lang.String[] { "AmtQty", "AmtAmt", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
