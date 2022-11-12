package com.juct.reggie.dto;


import com.juct.reggie.domain.Setmeal;
import com.juct.reggie.domain.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {
    //套餐关联的菜品 （查看、修改套餐使用）
    private List<SetmealDish> setmealDishes;
    //套餐关联的分类名称（列表展示使用）
    private String categoryName;
}
