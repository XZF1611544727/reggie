package com.juct.reggie.converter;

import com.juct.reggie.domain.Dish;
import com.juct.reggie.dto.DishDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-19 22:30
 */
@Mapper
public interface DishConvertor {
    DishConvertor INSTANCE = Mappers.getMapper(DishConvertor.class);

    DishDto toDtos(Dish dish);

    Dish toPo(DishDto dishDto);

    List<DishDto> toDtoList(List<Dish> dish);
}
