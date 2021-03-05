<%@ page language="java"%>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
        if(request.getSession().getAttribute("username") == null){
            response.sendRedirect(request.getContextPath()+"/login");
        }else{
            username = request.getSession().getAttribute("username").toString();
            role = Integer.parseInt(request.getSession().getAttribute("role").toString());
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
          <div class="col-8">
            <h3>Cards</h3>
            <p>This page provides you access to various project, Use the cards to navigate through this projects.</p>
          </div>
        </div>
      </div>
    <br/>
    <div class="container">
      <div class="row">
<!--         <div class="col-sm">
          <div class="card" style="width: 18rem;">
              <img src="/resources/images/card_1.png" class="card-img-top" alt="..." width="250" height="200">
              <div class="card-body">
                <h5 class="card-title">RUN INFERENCE ON FPGA</h5>
                <p class="card-text">Quantize and compile your model then run inference on FPGA.</p>
                <a href="<%= request.getContextPath()%>/cards?page=terminal" class="btn btn-primary">Try out</a>
              </div>
            </div>
        </div> -->
        <div class="col-sm">
          <div class="card" style="width: 18rem;">
              <img src="/resources/images/card_2.png" class="card-img-top" alt="... " width="250" height="200">
              <div class="card-body">
                <h5 class="card-title">Convert PPTX to VIDEO</h5>
                <p class="card-text">Enable your presentation to everyone on all platforms</p>
                <a href="<%= request.getContextPath()%>/cards?page=pptximage" class="btn btn-primary">Try out</a>
              </div>
            </div>
        </div>
        <div class="col-sm">
          <div class="card" style="width: 18rem;">
              <img src="/resources/images/card_3.png" class="card-img-top" alt="..." width="250" height="200">
              <div class="card-body">
                <h5 class="card-title">SOBEL Filter</h5>
                <p class="card-text">Applies the sobel operator to Input.</p>
                <a href="<%= request.getContextPath()%>/cards?page=ffmpegfilter" class="btn btn-primary">Try out</a>
              </div>
            </div>
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
