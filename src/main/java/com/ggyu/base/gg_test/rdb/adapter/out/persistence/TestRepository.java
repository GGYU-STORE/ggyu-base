package com.ggyu.base.gg_test.rdb.adapter.out.persistence;

import com.ggyu.base.gg_test.rdb.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO: Querydsl 주입받도록 변경(interface -> class)
 */
public interface TestRepository extends JpaRepository<TestEntity, Long> {
}
