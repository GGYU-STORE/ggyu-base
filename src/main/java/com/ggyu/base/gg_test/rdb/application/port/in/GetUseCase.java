package com.ggyu.base.gg_test.rdb.application.port.in;

import com.ggyu.base.gg_test.rdb.domain.Test;

import java.util.List;

public interface GetUseCase {
    Test getOne(Long id);

    List<Test> getAll();

}
