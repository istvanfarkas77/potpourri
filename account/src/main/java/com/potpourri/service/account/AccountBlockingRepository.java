package com.potpourri.service.account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountBlockingRepository /*implements CrudRepository<AccountEntity, String> */{

    Map<String, AccountEntity> data = new HashMap<>();

    public AccountBlockingRepository() {
        data.put("12345", new AccountEntity("A","12345",300L));
        data.put("23456", new AccountEntity("B","23456",400L));
        data.put("34567", new AccountEntity("C","34567",500L));
    }

    //@Override
    public <S extends AccountEntity> S save(S entity) {
        BusySleep.sleep(500);

        data.put(entity.getId(), entity);

        return entity;
    }

//    @Override
    public Optional<AccountEntity> findById(String s) {
        BusySleep.sleep(500);

        return Optional.of(data.get(s));
    }

//    @Override
    public Iterable<AccountEntity> findAll() {
        BusySleep.sleep(500);

        return data.values();
    }

//    @Override
    public long count() {
        BusySleep.sleep(500);

        return data.size();
    }

//    @Override
//    public <S extends AccountEntity> Iterable<S> saveAll(Iterable<S> entities) {
//        return null;
//    }

//    @Override
//    public boolean existsById(String s) {
//        return false;
//    }

//    @Override
//    public Iterable<AccountEntity> findAllById(Iterable<String> strings) {
//        return null;
//    }

//    @Override
//    public void deleteById(String s) {
//
//    }

//    @Override
//    public void delete(AccountEntity entity) {
//
//    }

//    @Override
//    public void deleteAll(Iterable<? extends AccountEntity> entities) {
//
//    }

//    @Override
//    public void deleteAll() {
//
//    }
}
