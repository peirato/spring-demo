package peirato.spring.springbucks.customer.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import peirato.spring.springbucks.customer.model.Coffee;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class FallbackCoffeeService implements CoffeeService {
    @Override
    public List<Coffee> getAll() {
        log.warn("Fallback to EMPTY menu.");
        return Collections.emptyList();
    }

    @Override
    public Coffee getById(Long id) {
        return null;
    }

    @Override
    public Coffee getByName(String name) {
        return null;
    }
}
