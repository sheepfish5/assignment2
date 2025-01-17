// Code generated by protoc-gen-go. DO NOT EDIT.
// versions:
// 	protoc-gen-go v1.33.0
// 	protoc        v5.26.0
// source: student_service.proto

package stuservice

import (
	protoreflect "google.golang.org/protobuf/reflect/protoreflect"
	protoimpl "google.golang.org/protobuf/runtime/protoimpl"
	timestamppb "google.golang.org/protobuf/types/known/timestamppb"
	wrapperspb "google.golang.org/protobuf/types/known/wrapperspb"
	reflect "reflect"
	sync "sync"
)

const (
	// Verify that this generated code is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(20 - protoimpl.MinVersion)
	// Verify that runtime/protoimpl is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(protoimpl.MaxVersion - 20)
)

type Student struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	// student id
	Id int64 `protobuf:"varint,1,opt,name=id,proto3" json:"id,omitempty"`
	// student name
	Name string `protobuf:"bytes,2,opt,name=name,proto3" json:"name,omitempty"`
	// student gender. male or female
	Gender string `protobuf:"bytes,3,opt,name=gender,proto3" json:"gender,omitempty"`
	// student birthday
	Birthday *timestamppb.Timestamp `protobuf:"bytes,4,opt,name=birthday,proto3" json:"birthday,omitempty"`
}

func (x *Student) Reset() {
	*x = Student{}
	if protoimpl.UnsafeEnabled {
		mi := &file_student_service_proto_msgTypes[0]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *Student) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*Student) ProtoMessage() {}

func (x *Student) ProtoReflect() protoreflect.Message {
	mi := &file_student_service_proto_msgTypes[0]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use Student.ProtoReflect.Descriptor instead.
func (*Student) Descriptor() ([]byte, []int) {
	return file_student_service_proto_rawDescGZIP(), []int{0}
}

func (x *Student) GetId() int64 {
	if x != nil {
		return x.Id
	}
	return 0
}

func (x *Student) GetName() string {
	if x != nil {
		return x.Name
	}
	return ""
}

func (x *Student) GetGender() string {
	if x != nil {
		return x.Gender
	}
	return ""
}

func (x *Student) GetBirthday() *timestamppb.Timestamp {
	if x != nil {
		return x.Birthday
	}
	return nil
}

var File_student_service_proto protoreflect.FileDescriptor

var file_student_service_proto_rawDesc = []byte{
	0x0a, 0x15, 0x73, 0x74, 0x75, 0x64, 0x65, 0x6e, 0x74, 0x5f, 0x73, 0x65, 0x72, 0x76, 0x69, 0x63,
	0x65, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x12, 0x0a, 0x73, 0x74, 0x75, 0x73, 0x65, 0x72, 0x76,
	0x69, 0x63, 0x65, 0x1a, 0x1f, 0x67, 0x6f, 0x6f, 0x67, 0x6c, 0x65, 0x2f, 0x70, 0x72, 0x6f, 0x74,
	0x6f, 0x62, 0x75, 0x66, 0x2f, 0x74, 0x69, 0x6d, 0x65, 0x73, 0x74, 0x61, 0x6d, 0x70, 0x2e, 0x70,
	0x72, 0x6f, 0x74, 0x6f, 0x1a, 0x1e, 0x67, 0x6f, 0x6f, 0x67, 0x6c, 0x65, 0x2f, 0x70, 0x72, 0x6f,
	0x74, 0x6f, 0x62, 0x75, 0x66, 0x2f, 0x77, 0x72, 0x61, 0x70, 0x70, 0x65, 0x72, 0x73, 0x2e, 0x70,
	0x72, 0x6f, 0x74, 0x6f, 0x22, 0x7d, 0x0a, 0x07, 0x53, 0x74, 0x75, 0x64, 0x65, 0x6e, 0x74, 0x12,
	0x0e, 0x0a, 0x02, 0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x03, 0x52, 0x02, 0x69, 0x64, 0x12,
	0x12, 0x0a, 0x04, 0x6e, 0x61, 0x6d, 0x65, 0x18, 0x02, 0x20, 0x01, 0x28, 0x09, 0x52, 0x04, 0x6e,
	0x61, 0x6d, 0x65, 0x12, 0x16, 0x0a, 0x06, 0x67, 0x65, 0x6e, 0x64, 0x65, 0x72, 0x18, 0x03, 0x20,
	0x01, 0x28, 0x09, 0x52, 0x06, 0x67, 0x65, 0x6e, 0x64, 0x65, 0x72, 0x12, 0x36, 0x0a, 0x08, 0x62,
	0x69, 0x72, 0x74, 0x68, 0x64, 0x61, 0x79, 0x18, 0x04, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x1a, 0x2e,
	0x67, 0x6f, 0x6f, 0x67, 0x6c, 0x65, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x62, 0x75, 0x66, 0x2e,
	0x54, 0x69, 0x6d, 0x65, 0x73, 0x74, 0x61, 0x6d, 0x70, 0x52, 0x08, 0x62, 0x69, 0x72, 0x74, 0x68,
	0x64, 0x61, 0x79, 0x32, 0x9d, 0x02, 0x0a, 0x0a, 0x73, 0x74, 0x75, 0x73, 0x65, 0x72, 0x76, 0x69,
	0x63, 0x65, 0x12, 0x3f, 0x0a, 0x0a, 0x61, 0x64, 0x64, 0x53, 0x74, 0x75, 0x64, 0x65, 0x6e, 0x74,
	0x12, 0x13, 0x2e, 0x73, 0x74, 0x75, 0x73, 0x65, 0x72, 0x76, 0x69, 0x63, 0x65, 0x2e, 0x53, 0x74,
	0x75, 0x64, 0x65, 0x6e, 0x74, 0x1a, 0x1a, 0x2e, 0x67, 0x6f, 0x6f, 0x67, 0x6c, 0x65, 0x2e, 0x70,
	0x72, 0x6f, 0x74, 0x6f, 0x62, 0x75, 0x66, 0x2e, 0x42, 0x6f, 0x6f, 0x6c, 0x56, 0x61, 0x6c, 0x75,
	0x65, 0x22, 0x00, 0x12, 0x3f, 0x0a, 0x09, 0x71, 0x75, 0x65, 0x72, 0x79, 0x42, 0x79, 0x49, 0x44,
	0x12, 0x1b, 0x2e, 0x67, 0x6f, 0x6f, 0x67, 0x6c, 0x65, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x62,
	0x75, 0x66, 0x2e, 0x49, 0x6e, 0x74, 0x36, 0x34, 0x56, 0x61, 0x6c, 0x75, 0x65, 0x1a, 0x13, 0x2e,
	0x73, 0x74, 0x75, 0x73, 0x65, 0x72, 0x76, 0x69, 0x63, 0x65, 0x2e, 0x53, 0x74, 0x75, 0x64, 0x65,
	0x6e, 0x74, 0x22, 0x00, 0x12, 0x44, 0x0a, 0x0b, 0x71, 0x75, 0x65, 0x72, 0x79, 0x42, 0x79, 0x4e,
	0x61, 0x6d, 0x65, 0x12, 0x1c, 0x2e, 0x67, 0x6f, 0x6f, 0x67, 0x6c, 0x65, 0x2e, 0x70, 0x72, 0x6f,
	0x74, 0x6f, 0x62, 0x75, 0x66, 0x2e, 0x53, 0x74, 0x72, 0x69, 0x6e, 0x67, 0x56, 0x61, 0x6c, 0x75,
	0x65, 0x1a, 0x13, 0x2e, 0x73, 0x74, 0x75, 0x73, 0x65, 0x72, 0x76, 0x69, 0x63, 0x65, 0x2e, 0x53,
	0x74, 0x75, 0x64, 0x65, 0x6e, 0x74, 0x22, 0x00, 0x30, 0x01, 0x12, 0x47, 0x0a, 0x0a, 0x64, 0x65,
	0x6c, 0x65, 0x74, 0x65, 0x42, 0x79, 0x49, 0x44, 0x12, 0x1b, 0x2e, 0x67, 0x6f, 0x6f, 0x67, 0x6c,
	0x65, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x62, 0x75, 0x66, 0x2e, 0x49, 0x6e, 0x74, 0x36, 0x34,
	0x56, 0x61, 0x6c, 0x75, 0x65, 0x1a, 0x1a, 0x2e, 0x67, 0x6f, 0x6f, 0x67, 0x6c, 0x65, 0x2e, 0x70,
	0x72, 0x6f, 0x74, 0x6f, 0x62, 0x75, 0x66, 0x2e, 0x42, 0x6f, 0x6f, 0x6c, 0x56, 0x61, 0x6c, 0x75,
	0x65, 0x22, 0x00, 0x42, 0x2b, 0x5a, 0x29, 0x73, 0x68, 0x65, 0x65, 0x70, 0x66, 0x69, 0x73, 0x68,
	0x35, 0x2e, 0x63, 0x6f, 0x6d, 0x2f, 0x67, 0x6f, 0x73, 0x65, 0x72, 0x76, 0x65, 0x72, 0x2f, 0x70,
	0x72, 0x6f, 0x74, 0x6f, 0x73, 0x2f, 0x73, 0x74, 0x75, 0x73, 0x65, 0x72, 0x76, 0x69, 0x63, 0x65,
	0x62, 0x06, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x33,
}

var (
	file_student_service_proto_rawDescOnce sync.Once
	file_student_service_proto_rawDescData = file_student_service_proto_rawDesc
)

func file_student_service_proto_rawDescGZIP() []byte {
	file_student_service_proto_rawDescOnce.Do(func() {
		file_student_service_proto_rawDescData = protoimpl.X.CompressGZIP(file_student_service_proto_rawDescData)
	})
	return file_student_service_proto_rawDescData
}

var file_student_service_proto_msgTypes = make([]protoimpl.MessageInfo, 1)
var file_student_service_proto_goTypes = []interface{}{
	(*Student)(nil),                // 0: stuservice.Student
	(*timestamppb.Timestamp)(nil),  // 1: google.protobuf.Timestamp
	(*wrapperspb.Int64Value)(nil),  // 2: google.protobuf.Int64Value
	(*wrapperspb.StringValue)(nil), // 3: google.protobuf.StringValue
	(*wrapperspb.BoolValue)(nil),   // 4: google.protobuf.BoolValue
}
var file_student_service_proto_depIdxs = []int32{
	1, // 0: stuservice.Student.birthday:type_name -> google.protobuf.Timestamp
	0, // 1: stuservice.stuservice.addStudent:input_type -> stuservice.Student
	2, // 2: stuservice.stuservice.queryByID:input_type -> google.protobuf.Int64Value
	3, // 3: stuservice.stuservice.queryByName:input_type -> google.protobuf.StringValue
	2, // 4: stuservice.stuservice.deleteByID:input_type -> google.protobuf.Int64Value
	4, // 5: stuservice.stuservice.addStudent:output_type -> google.protobuf.BoolValue
	0, // 6: stuservice.stuservice.queryByID:output_type -> stuservice.Student
	0, // 7: stuservice.stuservice.queryByName:output_type -> stuservice.Student
	4, // 8: stuservice.stuservice.deleteByID:output_type -> google.protobuf.BoolValue
	5, // [5:9] is the sub-list for method output_type
	1, // [1:5] is the sub-list for method input_type
	1, // [1:1] is the sub-list for extension type_name
	1, // [1:1] is the sub-list for extension extendee
	0, // [0:1] is the sub-list for field type_name
}

func init() { file_student_service_proto_init() }
func file_student_service_proto_init() {
	if File_student_service_proto != nil {
		return
	}
	if !protoimpl.UnsafeEnabled {
		file_student_service_proto_msgTypes[0].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*Student); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
	}
	type x struct{}
	out := protoimpl.TypeBuilder{
		File: protoimpl.DescBuilder{
			GoPackagePath: reflect.TypeOf(x{}).PkgPath(),
			RawDescriptor: file_student_service_proto_rawDesc,
			NumEnums:      0,
			NumMessages:   1,
			NumExtensions: 0,
			NumServices:   1,
		},
		GoTypes:           file_student_service_proto_goTypes,
		DependencyIndexes: file_student_service_proto_depIdxs,
		MessageInfos:      file_student_service_proto_msgTypes,
	}.Build()
	File_student_service_proto = out.File
	file_student_service_proto_rawDesc = nil
	file_student_service_proto_goTypes = nil
	file_student_service_proto_depIdxs = nil
}
