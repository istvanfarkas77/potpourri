package com.potpourri.service.account;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository /*implements ReactiveCrudRepository<AccountEntity, String>*/ {

    Map<String, AccountEntity> data = new HashMap<>();

    public AccountRepository() {
        data.put("12345", new AccountEntity("A","12345",300L));
        data.put("23456", new AccountEntity("B","23456",400L));
        data.put("34567", new AccountEntity("C","34567",500L));
    }

//    @Override
    public <S extends AccountEntity> Mono<S> save(S entity) {
        return Mono.just(entity).map(e -> {data.put(e.getId(), e); return e;}).delaySubscription(Duration.ofMillis(500));
    }

//    @Override

    public Mono<AccountEntity> findById(String s) {
        return Mono.just(data.get(s)).delaySubscription(Duration.ofMillis(500));
    }

//    @Override
    public Mono<AccountEntity> findById(Publisher<String> id) {
        return Mono.just(data.get(id)).delaySubscription(Duration.ofMillis(500));
    }

    //    @Override
    public Flux<AccountEntity> findAll() {
        return Flux.fromIterable(data.values()).delaySubscription(Duration.ofMillis(500));
    }
    //    @Override
    public Mono<Long> count() {
        return Mono.just(Long.valueOf(data.size())).delaySubscription(Duration.ofMillis(500));
    }

    //    @Override
    public <S extends AccountEntity> Flux<S> saveAll(Iterable<S> entities) {
        return null;
    }

    //    @Override
    public <S extends AccountEntity> Flux<S> saveAll(Publisher<S> entityStream) {
        return null;
    }

//    @Override
//    public Mono<Boolean> existsById(String s) {
//        return null;
//    }

//    @Override
//    public Mono<Boolean> existsById(Publisher<String> id) {
//        return null;
//    }

//    @Override
//    public Flux<AccountEntity> findAllById(Iterable<String> strings) {
//        return null;
//    }

//    @Override
//    public Flux<AccountEntity> findAllById(Publisher<String> idStream) {
//        return null;
//    }

//    @Override
//    public Mono<Void> deleteById(String s) {
//        return null;
//    }

//    @Override
//    public Mono<Void> deleteById(Publisher<String> id) {
//        return null;
//    }
//
//    @Override
//    public Mono<Void> delete(AccountEntity entity) {
//        return null;
//    }
//
//    @Override
//    public Mono<Void> deleteAll(Iterable<? extends AccountEntity> entities) {
//        return null;
//    }
//
//    @Override
//    public Mono<Void> deleteAll(Publisher<? extends AccountEntity> entityStream) {
//        return null;
//    }

//    @Override
//    public Mono<Void> deleteAll() {
//        return null;
//    }
}
