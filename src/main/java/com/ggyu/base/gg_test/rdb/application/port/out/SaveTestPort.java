package com.ggyu.base.gg_test.rdb.application.port.out;

import com.ggyu.base.gg_test.rdb.adapter.in.form.TestForm;
import com.ggyu.base.gg_test.rdb.domain.Test;

public interface SaveTestPort {
    void save(Test test);
}
