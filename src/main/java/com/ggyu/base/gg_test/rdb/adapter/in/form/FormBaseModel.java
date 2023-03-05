package com.ggyu.base.gg_test.rdb.adapter.in.form;

import com.ggyu.base.global.DomainBaseModel;

public interface FormBaseModel<T extends DomainBaseModel> {

    T getDomainModel();
}
