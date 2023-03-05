package com.ggyu.base.gg_test.rdb.adapter.in.form;

import com.ggyu.base.gg_test.rdb.domain.Test;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TestForm implements FormBaseModel<Test> {

    @NotBlank
    private String name;

    @Override
    public Test getDomainModel() {
        return Test.builder()
                .name(this.name)
                .build();
    }
}
