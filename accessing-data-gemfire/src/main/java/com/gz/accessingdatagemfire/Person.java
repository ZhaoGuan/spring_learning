package com.gz.accessingdatagemfire;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;

@Region(value = "People")
@Data
public class Person implements Serializable {
    @Id
    @Getter
    private final String name;

    @Getter
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return String.format("%s is %d years old", getName(), getAge());
    }
}
