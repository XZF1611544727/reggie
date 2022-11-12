package com.juct.reggie.dto;


import com.juct.reggie.domain.OrderDetail;
import com.juct.reggie.domain.Orders;
import lombok.Data;
import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
