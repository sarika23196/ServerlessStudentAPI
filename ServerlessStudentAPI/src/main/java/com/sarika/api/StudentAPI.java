package com.sarika.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.sarika.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StudentAPI {

    private Logger logger = LogManager.getLogger(StudentAPI.class);

    public static Student handleRequest(Request request, Context context) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        Student student = null;

        switch(request.getHttpMethod()) {
            case"GET":
                student = mapper.load(Student.class, request.getId());
                if (student == null) {
                    throw new RuntimeException("Not Found");
                }
                return student;

            case "POST":
                student = request.getStudent();
                mapper.save(student);

        }
        return null;
    }
}
