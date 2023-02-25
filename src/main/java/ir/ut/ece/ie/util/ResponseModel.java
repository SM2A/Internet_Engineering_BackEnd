package ir.ut.ece.ie.util;

import lombok.Data;

@Data
public class ResponseModel {
    private Boolean success;
    private Object data;
}
