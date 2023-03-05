package com.ggyu.base.gg_test.rdb.adapter.out.persistence;

import com.ggyu.base.global.JpaBaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "test", catalog = "db_test")
@Getter
public class TestEntity implements JpaBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private LocalDateTime updatedAt;

    public static TestEntity from(String name) {
        return new TestEntity(name);
    }

    public TestEntity(String name) {
        this.name = name;
        this.updatedAt = LocalDateTime.now();
    }
}
