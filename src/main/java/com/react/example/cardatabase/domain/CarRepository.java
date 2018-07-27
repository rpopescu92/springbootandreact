package com.react.example.cardatabase.domain;

import com.react.example.cardatabase.domain.entity.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    @Query(value = "select * from car c where c.brand = ?1", nativeQuery = true)
    List<Car> findByBrand(String brand);

    List<Car> findByColor(String color);

    List<Car> findByYear(int year);

    List<Car> findByBrandAndModel(String brand, String model);

    List<Car> findByBrandOrColor(String brand, String color);

    List<Car> findByBrandOrderByYearAsc(String brand);

}
