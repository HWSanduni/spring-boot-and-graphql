package com.digiratina.task.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseDTO {

    private String message;
    private Object data;

}
