package edu.study.pasudo123.redissample.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@Getter
@ToString(exclude = {
        "date"
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RedisDummy {

    private String line;
    private int number;
    private String detailDate;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    public RedisDummy(final String line,
                      final Integer number) {

        this.line = line;
        this.number = number;
        this.date = LocalDateTime.now();
    }

    public RedisDummy(final LinkedHashMap<String, Object> map){

        this.line = (String) map.get("line");
        this.number = (Integer) map.get("number");
        this.detailDate = (String) map.get("date");
    }
}
