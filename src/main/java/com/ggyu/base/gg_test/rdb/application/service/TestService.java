package com.ggyu.base.gg_test.rdb.application.service;

import com.ggyu.base.gg_test.rdb.adapter.in.form.TestForm;
import com.ggyu.base.gg_test.rdb.application.port.in.GetUseCase;
import com.ggyu.base.gg_test.rdb.application.port.in.SaveUseCase;
import com.ggyu.base.gg_test.rdb.application.port.out.LoadTestPort;
import com.ggyu.base.gg_test.rdb.application.port.out.SaveTestPort;
import com.ggyu.base.gg_test.rdb.domain.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService implements GetUseCase, SaveUseCase {

    private final LoadTestPort loadTestPort;
    private final SaveTestPort saveTestPort;

    @Override
    public Test getOne(Long id) {
        return loadTestPort.load(id);
    }

    @Override
    public List<Test> getAll() {
        return loadTestPort.loadAll();
    }

    @Override
    public Test create(TestForm form) {
        Test test = form.getDomainModel();
        saveTestPort.save(test);

        return test;
    }

    @Override
    public Test update(Long id, TestForm form) {
        Test test = loadTestPort.load(id);

        // Test test = form.getDomainModel();
        // test
        // UseCase 계층에서 VO의 데이터를 꺼내서 Entity를 생성
        // -> VO의 변경에 의해 Entity가 변경할 가능성을 낮출
        test.changeName(form.getName());
        saveTestPort.save(test);

        return test;
    }
}
