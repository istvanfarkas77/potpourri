package com.potpourri.service.account;

//import lombok.Builder;
//import lombok.NoArgsConstructor;
import lombok.Value;


@Value
public class Account {

    private String accountNumber;
    private String owner;
    private Long limit;
}
