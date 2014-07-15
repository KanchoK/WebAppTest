package com.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;


/**
 * Created by R500 on 15.7.2014 г..
 */
public class jTableController extends HttpServlet{

    public jTableController() {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        if(request.getParameter("action") != null){
            String action = (String)request.getParameter("action");
            List<Customer> customers = CrudDao.getAllCustomers();
            Gson gson = new Gson();
            response.setContentType("application/json");

            if(action.equals("list")){
                try{
                    JsonElement element = gson.toJsonTree(customers, new TypeToken<List<Customer>>() {}.getType());
                    JsonArray jsonArray = element.getAsJsonArray();
                    String listData=jsonArray.toString();

                    listData="{\"Result\":\"OK\",\"Records\":"+listData+"}";
                    response.setContentType("application/json");
                    response.getWriter().print(listData);
//                    System.out.println(listData);
                }catch(Exception ex){
                    String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace()+"}";
                    try {
                        response.getWriter().print(error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            else if (action.equals("update")){
                Customer customer = new Customer();
                customer.setCustomerID(Integer.parseInt(request.getParameter("customerID")));
                customer.setCustomerName(request.getParameter("customerName"));
                customer.setCity(request.getParameter("City"));

                CrudDao.updateCustomer(customer);
                String listData="{\"Result\":\"OK\"}";
                try {
                    response.getWriter().print(listData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else if (action.equals("create")){
                Customer customer = new Customer();

                customer.setCustomerName(request.getParameter("customerName"));
                customer.setCity(request.getParameter("City"));

                try {
                    customer.setCustomerID(CrudDao.addCustomer(customer));
                    customers.add(customer);
                    String json = gson.toJson(customer);
                    String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
                    try {
                        response.getWriter().print(listData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }catch (Exception ex){
                    String error = "{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace()+"}";
                    try {
                        response.getWriter().print(error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            else if (action.equals("delete")){
                try{
                    if(request.getParameter("customerID")!=null){
                        String customerID = (String)request.getParameter("customerID");
                        CrudDao.deleteCustomer(Integer.parseInt(customerID));
                        String listData="{\"Result\":\"OK\"}";
                        response.getWriter().print(listData);
                    }
                }catch(Exception ex){
                    String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
                    try {
                        response.getWriter().print(error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
