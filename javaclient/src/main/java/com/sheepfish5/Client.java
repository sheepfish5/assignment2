package com.sheepfish5;

import java.util.Iterator;

import com.google.protobuf.BoolValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import com.google.protobuf.Timestamp;
import com.sheepfish5.gui.ClientUI;
import com.sheepfish5.protos.Student;
import com.sheepfish5.protos.stuserviceGrpc;
import com.sheepfish5.protos.stuserviceGrpc.stuserviceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */
@Slf4j
public class Client 
{
    private stuserviceBlockingStub stub;

    public Client(String host, int port) {
        log.info("init client...");
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        stub = stuserviceGrpc.newBlockingStub(channel);
    }

    public void addStudent(String date) {
        Timestamp birthday = ClientUtil.GetTimestampFromDate(date);
        Student stu = Student.newBuilder().setName("Hello World")
                .setGender("male")
                .setBirthday(birthday)
                .build();
        BoolValue result = stub.addStudent(stu);
        log.info("invocate addStudent({}), return {}",date, result.getValue());
    }

    public void queryById(long id) {
        Int64Value id64 = Int64Value.newBuilder().setValue(id).build();
        Student stu = stub.queryByID(id64);
        log.info("invocate queryById({}), return {}", id, stu.toString());
    }

    public void queryByName(String name) {
        StringValue nameValue = StringValue.newBuilder().setValue(name).build();
        try{
            Iterator<Student> stus = stub.queryByName(nameValue);
            for (int i = 1; stus.hasNext(); i++) {
                log.info("queryByName: receive {}: {}", i, stus.next());
            }
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        Int64Value id64 = Int64Value.newBuilder().setValue(id).build();
        BoolValue result = stub.deleteByID(id64);
        if (result.getValue()) {
            log.info("deleteById: successful to delete id[{}]", id);
        } else {
            log.info("deleteById: failed to delete id[{}]", id);
        }
    }
    public static void main( String[] args )
    {
        log.info("java client.");
        // Client client = new Client("localhost", 18899);
        // client.queryById(1);
        // client.queryByName("avi");
        // Timestamp birthday = ClientUtil.GetTimestampFromDate("2001-02-01");
        // Student stu = Student.newBuilder()
        //         .setName("Hello World")
        //         .setGender("male")
        //         .setBirthday(birthday)
        //         .build();
        // client.addStudent("2001-02-01");
        // client.deleteById(12);
        ClientUI.startGUI();
    }
}
