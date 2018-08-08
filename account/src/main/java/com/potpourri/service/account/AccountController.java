package com.potpourri.service.account;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@AllArgsConstructor
@Slf4j
public class AccountController {

    private final AccountRepository accountRepository;

    private final AccountBlockingRepository accountBlockingRepository;

    @PostMapping(value = "/account/{owner}/{overdraftLimit}")
    public Mono<ResponseEntity<Account>> createAccount(@PathVariable("owner") String owner, @PathVariable("overdraftLimit") Long overdraftLimit) {

        try {
            String hostName = InetAddress.getLocalHost().getHostName();

            Mono<AccountEntity> mono = accountRepository.save(AccountEntity.builder().id(UUID.randomUUID().toString()).owner(owner).overdraftLimit(overdraftLimit).build());

            return mono.map(accountEntity ->
                    ResponseEntity.ok()
                            .contentType(APPLICATION_JSON)
                            .header("hostname", hostName)
                            .body(new Account(accountEntity.getId(), accountEntity.getOwner(), accountEntity.getOverdraftLimit())));
        } catch (Exception ex) {
            LOG.error("", ex);
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

    @GetMapping(value = "/account/{accountNumber}")
    public Mono<ResponseEntity<Account>> getAccount(@PathVariable String accountNumber) {

        try {
            String hostName = InetAddress.getLocalHost().getHostName();

            Mono<AccountEntity> mono = accountRepository.findById(accountNumber);

            return mono.map(accountEntity ->
                    ResponseEntity.ok()
                                .contentType(APPLICATION_JSON)
                                .header("hostname", hostName)
                                .body(new Account(accountEntity.getId(), accountEntity.getOwner(), accountEntity.getOverdraftLimit())));
        } catch (Exception ex) {
            LOG.error("", ex);
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

    @GetMapping(value = "/account")
    public Mono<ResponseEntity<List<Account>>> listAccount() {

        try {
            String hostName = InetAddress.getLocalHost().getHostName();

            Flux<Account> flux = accountRepository.findAll().flatMap((accountEntity) -> Mono.just(new Account(accountEntity.getId(), accountEntity.getOwner(), accountEntity.getOverdraftLimit())));

            return flux.collectList().map(accounts -> ResponseEntity.ok()
                    .contentType(APPLICATION_JSON)
                    .header("hostname", hostName)
                    .body(accounts));
        } catch (Exception ex) {
            LOG.error("", ex);
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

    @GetMapping(value = "/account-blocking")
    public ResponseEntity<List<Account>> listAccountNb() {

        try {
            Iterable<AccountEntity> iterable = accountBlockingRepository.findAll();

            return ResponseEntity
                    .ok()
                    .header("hostname", InetAddress.getLocalHost().getHostName())
                    .body(StreamSupport.stream(iterable.spliterator(), false)
                            .map(accountEntity -> new Account(accountEntity.getId(), accountEntity.getOwner(), accountEntity.getOverdraftLimit())).collect(Collectors.toList()));

        } catch (Exception ex) {
            LOG.error("", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
