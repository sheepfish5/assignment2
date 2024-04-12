package main

import (
	"context"
	"fmt"
	"log"
	"net"
	"os"

	"google.golang.org/grpc"
	"google.golang.org/protobuf/types/known/wrapperspb"
	"sheepfish5.com/goserver/db_access"
	"sheepfish5.com/goserver/protos/stuservice"
	"sheepfish5.com/goserver/util"
)

type stuserviceServer struct {
	stuservice.UnimplementedStuserviceServer
	dba db_access.DBAccess
}

func (s *stuserviceServer) AddStudent(ctx context.Context, stu *stuservice.Student) (*wrapperspb.BoolValue, error) {
	log.Printf("AddStudent is invocated.\n")
	var result wrapperspb.BoolValue
	insertId, _ := s.dba.AddStudent(stu)
	if insertId == 0 {
		result.Value = false
	} else {
		result.Value = true
	}
	return &result, nil
}

func (s *stuserviceServer) QueryByID(ctx context.Context, id *wrapperspb.Int64Value) (*stuservice.Student, error) {
	log.Printf("QueryByID is invocated.\n")
	stu := s.dba.QueryByID(id.Value)
	return stu, nil
}

func (s *stuserviceServer) QueryByName(name *wrapperspb.StringValue, stream stuservice.Stuservice_QueryByNameServer) error {
	log.Printf("QueryByName is invocated.\n")

	stus := s.dba.QueryByName(name.Value)
	for _, stu := range stus {
		stream.Send(stu)
	}
	return nil
}

func (s *stuserviceServer) DeleteByID(ctx context.Context, id *wrapperspb.Int64Value) (*wrapperspb.BoolValue, error) {
	log.Printf("DeleteByID is invocated.\n")

	var result wrapperspb.BoolValue
	rows := s.dba.DeleteByID(id.Value)
	if rows == 0 {
		result.Value = false
	} else {
		result.Value = true
	}
	return &result, nil
}

func StuToString(stu *stuservice.Student) string {
	return fmt.Sprintf("id=%d, name=%s, gender=%s, birthday=%s", 
			stu.Id, stu.Name, stu.Gender, util.GetDateString(stu.Birthday))
}

func newStuServiceServer() *stuserviceServer {
	var service stuserviceServer
	service.dba.InitDB(
		os.Getenv("DBUSER"),
		os.Getenv("DBPASS"),
		os.Getenv("DBNAME"),
		os.Getenv("DBADDR"),
	)
	return &service
}

func main() {
	lis, err := net.Listen("tcp", ":18899")
	if err != nil {
		log.Fatalf("failed to listem on 18899: %v", err)
	}
	log.Printf("listen on %v\n", lis.Addr())
	grpcServer := grpc.NewServer()
	stuservice.RegisterStuserviceServer(grpcServer, newStuServiceServer())
	grpcServer.Serve(lis)
}