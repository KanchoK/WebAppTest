package com.web;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by R500 on 10.7.2014 г..
 */

public class CRUDController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("getAllUsers")){

            List<User> listUsers = CrudDao.getAllUsers();

            JSONObject objUsers = new JSONObject();
            JSONArray array = new JSONArray();

            for(int i = 0; i < listUsers.size(); i++){
                JSONObject rowObj = new JSONObject();
                rowObj.put("ID", ((User)listUsers.get(i)).getUserID());
                rowObj.put("username", ((User)listUsers.get(i)).getUsername());
                rowObj.put("password", ((User)listUsers.get(i)).getPassword());
                array.put(rowObj);
            }

            objUsers.put("rows",array);
            PrintWriter out = response.getWriter();
            out.println(objUsers);
            System.out.println(objUsers);

//            Gson gson = new Gson();
//            JsonElement element = gson.toJsonTree(listUsers, new TypeToken<List<User>>(){}.getType());
//            JsonArray jsonArray = element.getAsJsonArray();
//            String listData = jsonArray.toString();
//            listData = "{\"rows\":" + listData + "}";
//            response.setContentType("application/json");
//            response.getWriter().print(listData);
//            System.out.println(listData);

        }

        else if (operation.equals("delete")){
            User user = new User();
            user.setUserID(Integer.parseInt(request.getParameter("userID")));
            JSONObject objUser = new JSONObject();
            objUser.put("ID",user.getUserID());

            CrudDao.deleteUser(user);

            PrintWriter out = response.getWriter();
            out.println(objUser);
        }

        else if (operation.equals("add")){
            User user = new User();

            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setUserID(CrudDao.addUser(user));

            JSONObject objUser = new JSONObject();
            objUser.put("ID", user.getUserID());
            objUser.put("username", user.getUsername());
            objUser.put("password", user.getPassword());

            PrintWriter out = response.getWriter();
            out.println(objUser);
        }

        else if (operation.equals("edit")){
            User user = new User();

            user.setUserID(Integer.parseInt(request.getParameter("userID")));
            user.setUsername(request.getParameter("newUsername"));
            user.setPassword(request.getParameter("newPassword"));

            JSONObject objUser = new JSONObject();
            objUser.put("ID", user.getUserID());
            objUser.put("newUsername", user.getUsername());
            objUser.put("newPassword", user.getPassword());

            CrudDao.updateUser(user);

            PrintWriter out = response.getWriter();
            out.println(objUser);
        }
    }
}
