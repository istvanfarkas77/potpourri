package com.potpourri.service.account;

import lombok.Builder;
import lombok.Data;

//import javax.persistence.*;

//@Entity
//@Table(name = "account")
@Data
@Builder
public class AccountEntity {

//    @Id
//    @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
//    @Column(name = "account_number")
    private String id;
    private String owner;
//    @Column(name = "overdraft_limit")
    private Long overdraftLimit;
}
