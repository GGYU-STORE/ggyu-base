package com.ggyu.base.global;

public interface Mapper<T extends DomainBaseModel, U extends JpaBaseModel> {
    T toDomain(U obj);

    U toEntity(T obj);

}
