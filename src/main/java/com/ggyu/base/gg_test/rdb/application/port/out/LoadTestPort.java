package com.ggyu.base.gg_test.rdb.application.port.out;

import com.ggyu.base.gg_test.rdb.domain.Test;

import java.util.List;

public interface LoadTestPort {
    Test load(Long id);

    List<Test> loadAll();
}
