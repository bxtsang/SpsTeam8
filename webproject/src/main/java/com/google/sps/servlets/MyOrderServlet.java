package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.data.Category;
import com.google.sps.data.Room;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@WebServlet("/myOrder")
public class MyOrderServlet extends HttpServlet {
  // TODO
}
