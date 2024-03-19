package com.gugulethugillz.zip_metrics_tool.common;

import java.util.List;
import java.util.Set;

public interface GenericMapper<E, D> {
    E asEntity(D dto);

    D asDTO(E entity);

    List<E> asEntityList(List<D> dtoList);

    List<D> asDTOList(List<E> entityList);

    Set<E> asEntitySet(Set<D> dtoSet);

    Set<D> asDTOSet(Set<E> entitySet);



}
