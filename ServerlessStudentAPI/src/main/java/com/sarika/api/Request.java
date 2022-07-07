package com.sarika.api;

import com.sarika.model.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {

    private String httpMethod;
    private int id;
    private Student student;

}
