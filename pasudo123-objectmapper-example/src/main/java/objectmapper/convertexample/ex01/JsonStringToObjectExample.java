package objectmapper.convertexample.ex01;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonStringToObjectExample {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[]args) throws IOException {
        String json = "{\"name\":\"HONG\", \"age\":20}";

        Person person = mapper.readValue(json, Person.class);

        System.out.println(person.name);    // HONG
        System.out.println(person.age);     // 20
    }
}
