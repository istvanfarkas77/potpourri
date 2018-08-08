package com.potpourri.service.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableJpaRepositories
@EnableAsync
public class AccountApplication {

    @Bean
    AccountRepository accountRepository() {
        return new AccountRepository();
    }

    @Bean
    AccountBlockingRepository accountBlockingRepository() {
        return new AccountBlockingRepository();
    };

	@Bean
	AccountController accountController(AccountRepository accountRepository, AccountBlockingRepository accountBlockingRepository) {
		return new AccountController(accountRepository, accountBlockingRepository);
	}

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}
}
