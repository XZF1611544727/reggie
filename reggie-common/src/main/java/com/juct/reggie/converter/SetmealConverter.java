package com.juct.reggie.converter;

import com.juct.reggie.domain.Setmeal;
import com.juct.reggie.dto.SetmealDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-15 20:48
 */
//创建转换器类
@Mapper
public interface SetmealConverter {
    //创建一个映射器实例
    SetmealConverter INSTANCE = Mappers.getMapper(SetmealConverter.class);

    //Setmeal 转 SetmealDto
    SetmealDto toDTO(Setmeal setmeal);

    //SetmealDto 转 Setmeal
    Setmeal toPO(SetmealDto dto);

    //List<Setmeal> 转 List<SetmealDto>集合
    List<SetmealDto> toDtoList(List<Setmeal> setmeals);
}
