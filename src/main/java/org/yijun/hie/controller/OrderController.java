package org.yijun.hie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.EnterpriseProductEntity;
import org.yijun.hie.persistence.entity.OrderEntity;
import org.yijun.hie.persistence.entity.PaymentEntity;
import org.yijun.hie.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by liuyijun on 14-11-19.
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/choosePayment", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void chooseOnePayment(Model model, HttpServletRequest request) {
        OrderEntity orderEntity = orderService.getOrderByIDFromService(Integer.valueOf(request.getParameter("id")));
        HttpSession session = request.getSession();
        synchronized (session){
            OrderEntity orderEntity1 = (OrderEntity)session.getAttribute("selectOrder");
            if(orderEntity1!=null){
                session.removeAttribute("selectOrder");
                session.setAttribute("selectOrder",orderEntity);
            }else if(orderEntity1 == null){
                session.setAttribute("selectOrder",orderEntity);
            }
        }
    }

    @RequestMapping(value = "/makePayment", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void makePayment(HttpServletRequest request) {
        orderService.updatePaymentFromService(Integer.valueOf(request.getParameter("id")));
    }
}
