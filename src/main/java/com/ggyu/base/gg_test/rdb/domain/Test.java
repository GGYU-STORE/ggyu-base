package com.ggyu.base.gg_test.rdb.domain;

import com.ggyu.base.global.DomainBaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Test implements DomainBaseModel {
    private long id;
    private String name;

    public void changeName(String name) {
        this.name = name;
    }

    public void updateInfo(Test test) {
        this.name = test.name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
