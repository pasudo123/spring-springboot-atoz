package edu.study.pasudo123.app.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "@id",
        scope = Employee.class
)
public class Employee {

    private String empName;
    private String empId;

    @Builder
    public Employee(String empName, String empId) {
        this.empName = empName;
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "Employee [empName=" + empName + "], [empId=" + empId + "]";
    }
}
