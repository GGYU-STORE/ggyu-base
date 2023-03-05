package com.ggyu.base.gg_test.rdb.adapter.out.persistence;

import com.ggyu.base.gg_test.rdb.adapter.in.form.TestForm;
import com.ggyu.base.gg_test.rdb.domain.Test;
import com.ggyu.base.global.Mapper;
import org.springframework.stereotype.Component;

@Component
public class TestMapper implements Mapper<Test, TestEntity> {

    @Override
    public Test toDomain(TestEntity obj) {
        return Test.builder()
                .id(obj.getId())
                .name(obj.getName())
                .build();
    }

    @Override
    public TestEntity toEntity(Test obj) {
        return TestEntity.from(obj.getName());
    }

}
