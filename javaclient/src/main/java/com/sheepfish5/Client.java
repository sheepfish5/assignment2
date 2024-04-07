package com.sheepfish5;

import java.util.ArrayDeque;
import java.util.Deque;
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

    public Boolean addStudent(String name, String gender, String date) {
        Timestamp birthday = ClientUtil.GetTimestampFromDate(date);
        Student stu = Student.newBuilder().setName(name)
                .setGender(gender)
                .setBirthday(birthday)
                .build();
        BoolValue result = stub.addStudent(stu);
        log.info("invocate addStudent({}), return {}",date, result.getValue());
        return result.getValue();
    }

    public Student queryById(long id) {
        Int64Value id64 = Int64Value.newBuilder().setValue(id).build();
        Student stu;
        try {
            stu = stub.queryByID(id64);
        } catch (StatusRuntimeException e) {
            // TODO: handle exception
            log.info("invocating queryById failed: {}", e.getStatus());
            return null;
        }
        log.info("invocate queryById({}), return {}", id, stu.toString());
        return stu;
    }

    public Iterable<Student> queryByName(String name) {
        Deque<Student> result = new ArrayDeque<Student>();
        StringValue nameValue = StringValue.newBuilder().setValue(name).build();
        try{
            Iterator<Student> stus = stub.queryByName(nameValue);
            for (int i = 1; stus.hasNext(); i++) {
                Student stu = stus.next();
                log.info("queryByName: receive {}: {}", i, stu);
                result.add(stu);
            }
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
            return null;
        }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    public Boolean deleteById(long id) {
        Int64Value id64 = Int64Value.newBuilder().setValue(id).build();
        BoolValue result = stub.deleteByID(id64);
        if (result.getValue()) {
            log.info("deleteById: successful to delete id[{}]", id);
        } else {
            log.info("deleteById: failed to delete id[{}]", id);
        }
        return result.getValue();
    }
    public static void main( String[] args )
    {
        log.info("java client.");
        ClientUI.startGUI();
    }
}
