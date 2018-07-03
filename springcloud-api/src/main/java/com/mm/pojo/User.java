package com.mm.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.Format;
import java.time.LocalDateTime;

/**
 * @author lwl
 * @date 2018/7/3
 */
@Data
public class User {

    private Long id;

    private String name;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;
}
