package com.react.example.cardatabase.domain;

import com.react.example.cardatabase.domain.entity.Car;
import com.react.example.cardatabase.domain.entity.Owner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void saveCar() {
        Car car = new Car("Tesla", "Model X",
                "White", "ABC-1234", 2017, 86000, null);
        testEntityManager.persistAndFlush(car);
        assertThat(car.getId()).isNotNull();
    }

    @Test
    public void deleteCars() {
        testEntityManager.persistAndFlush(new Car("Tesla", "Model X",
                "White", "ABC-1234",
                2017, 86000, null));
        testEntityManager.persistAndFlush(new Car("Mini", "Cooper", "Yellow",
                "BWS-3007", 2015, 24500, null));

        carRepository.deleteAll();
        assertThat(carRepository.findAll()).isEmpty();
    }
}
