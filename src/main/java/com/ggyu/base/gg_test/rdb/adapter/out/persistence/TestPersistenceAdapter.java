package com.ggyu.base.gg_test.rdb.adapter.out.persistence;

import com.ggyu.base.gg_test.rdb.application.port.out.LoadTestPort;
import com.ggyu.base.gg_test.rdb.application.port.out.SaveTestPort;
import com.ggyu.base.gg_test.rdb.domain.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class TestPersistenceAdapter implements LoadTestPort, SaveTestPort {

    private final TestMapper testMapper;
    private final TestRepository testRepository;

    @Override
    public Test load(Long id) {
        TestEntity testEntity = testRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return testMapper.toDomain(testEntity);
    }

    @Override
    public List<Test> loadAll() {
        return testRepository.findAll()
                .stream()
                .map(testMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(@NotNull Test test) {
        TestEntity testEntity = testMapper.toEntity(test);
        testRepository.save(testEntity);
    }

}
