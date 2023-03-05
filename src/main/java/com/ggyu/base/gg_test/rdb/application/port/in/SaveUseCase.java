package com.ggyu.base.gg_test.rdb.application.port.in;

import com.ggyu.base.gg_test.rdb.adapter.in.form.TestForm;
import com.ggyu.base.gg_test.rdb.domain.Test;

public interface SaveUseCase {
    Test create(TestForm form);

    Test update(Long id, TestForm form);
}
