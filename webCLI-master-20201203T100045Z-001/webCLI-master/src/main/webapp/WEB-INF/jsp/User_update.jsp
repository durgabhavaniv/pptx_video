<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="stag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <!--  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <title>zlabs</title>
  </head>
  <body>
<%
    String username = null, admin = null;
    int role = 0;
        if(request.getSession().getAttribute("username") == null && request.getSession().getAttribute("role") == null){
            response.sendRedirect(request.getContextPath()+"/");
        }else{
            username = request.getSession().getAttribute("username").toString();
            role = Integer.parseInt(request.getSession().getAttribute("role").toString());
            if( role != 1 )
                response.sendRedirect(request.getContextPath()+"/");
        }
%>
    <!-- Image and text -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand" href="<%= request.getContextPath()%>/">
        <img src="/resources/images/zoho_logo.png" width="50" height="30" class="d-inline-block align-top" alt="">
        Zlabs
      </a>

      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div class="navbar-nav">
            <a class="nav-item nav-link active" href="<%= request.getContextPath()%>/">Home <span class="sr-only">(current)</span></a>
            <% if (role == 1 ) { %>
            <a class="nav-item nav-link" href="<%= request.getContextPath()%>/admin">Users</a>
            <% } %>
          </div>
        </div>

      <form class="form-inline my-2 my-lg-0" action="<%= request.getContextPath()%>/logout" method="get">
         <button class="btn btn-danger" type="submit">logout</button>
      </form>
    </nav>
    <br/>

    <div class="container">
      <div class="row">
        <div class="col">

        </div>
        <div class="col-8">
          <h2> User Information <h2>
        </div>
        <div class="col">

        </div>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="col">

        </div>
        <div class="col-8">
            <form action="<%= request.getContextPath()%>/add_user" method="post" >
              <div class="form-group row">
                <label for="inputEmail3" class="col-sm-2 col-form-label">Username</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" name="username" required value=${user.username} >
                </div>
              </div>
              <div class="form-group row">
                <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                  <input type="password" class="form-control" name="password" required value=${user.password} >
                </div>
              </div>
              <fieldset class="form-group">
                <div class="row">
                  <legend class="col-form-label col-sm-2 pt-0">Radios</legend>
                  <div class="col-sm-10">
                    <div class="form-check">
                      <input class="form-check-input" type="radio" name="role" id="role1" value=1 >
                      <label class="form-check-label" for="role1">
                        Admin
                      </label>
                        <br/>
                      <input class="form-check-input" type="radio" name="role" id="role2" value=2 checked >
                      <label class="form-check-label" for="role2">
                        User
                      </label>
                    </div>
                  </div>
                </div>
              </fieldset>
              <div class="form-group row">
                <div class="col-sm-10">
                  <button type="submit" class="btn btn-primary">Add User</button>
                </div>
              </div>
            </form>
        </div>
        <div class="col">

        </div>
      </div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <!--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>-->
    <script src="/resources/js/bootstrap.min.js"></script>
    </body>
</html>