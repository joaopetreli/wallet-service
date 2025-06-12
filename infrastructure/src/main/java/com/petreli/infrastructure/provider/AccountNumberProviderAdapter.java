package com.petreli.infrastructure.provider;

import com.petreli.application.exception.ValidationException;
import com.petreli.application.provider.AccountNumberProvider;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountNumberProviderAdapter implements AccountNumberProvider {

    private final StringRedisTemplate redisTemplate;

    @Override
    public Long nextNumber() {

        for (int attempt = 0; attempt < 10_000; attempt++) {
            Long sequence = redisTemplate.opsForValue().increment("account_number_seq:");

            if (sequence == null || sequence > 999999999) {
                throw new ValidationException("Account number limit reached.");
            }

            String accountNumberStr = String.format("%09d", sequence);
            String uniqueKey = "account_number:%s".formatted(accountNumberStr);

            boolean alreadyExists = redisTemplate.hasKey(uniqueKey);
            if (!alreadyExists) {
                redisTemplate.opsForValue().set(uniqueKey, "1");
                return Long.parseLong(accountNumberStr);
            }
        }

        throw new ValidationException("Failed to generate unique account number after multiple attempts.");
    }
}
