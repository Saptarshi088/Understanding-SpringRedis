package com.saptarshi.TryingOutRedis.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddStudentRequest implements Serializable {
    private String name;
    private int marks;
}
