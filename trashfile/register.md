<!doctype html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:fragment="head">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title th:text="${(title)}">Bootstrap demo</title>
<!--google font robot mono-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@500&display=swap" rel="stylesheet">
<!--end-google font-->
<!--icona-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
<!--end-icona-->
<!--bootstrap-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<!--end-bootstrap-->
<!--css-->
<link rel="stylesheet" href="style.css">
<!--end-css-->
</head>
<body>
<!-- navbar -->
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand">Simulator</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="#navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">crea un articolo</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">help</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">register</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">logout</a>
                </li>
            </ul>
            <!-- Back Button -->
            <a class="navbar-brand" href="javascript:history.back()">Back</a>
        </div>
    </div>
</nav>

<div class="vh-100">
    <br><br />
    <div class="container">
        <div class="row col-md-8 offset-md-2">
            <div class="card">
                <div class="card-header">
                    <h2 class="text-center">Registration</h2>
                </div>
                <div th:if="${param.success}">
                    <div class="alert alert-info">
                        You have successfully registered our app!
                    </div>
                </div>
                <div class="card-body">
                    <form method="post" th:action="@{/register/save}" th:object="${user}">
                        <div class="form-group mb-3">
                            <label class="form-label">First Name:</label>
                            <input class="form-control" id="firstName" name="firstName" placeholder="Enter first name" th:field="*{firstName}" type="text"/>
                            <p class="text-danger" th:errors="*{firstName}" th:if="${#fields.hasErrors('firstName')}"></p>
                        </div>
                        <div class="form-group mb-3">
                            <label class="form-label">Last Name:</label>
                            <input class="form-control" id="lastName" name="lastName" placeholder="Enter last name" th:field="*{lastName}" type="text"/>
                            <p class="text-danger" th:errors="*{lastName}" th:if="${#fields.hasErrors('lastName')}"></p>
                        </div>
                        <div class="form-group mb-3">
                            <label class="form-label">Email:</label>
                            <input class="form-control" id="email" name="email" placeholder="Enter email address" th:field="*{email}" type="email"/>
                            <p class="text-danger" th:errors="*{email}" th:if="${#fields.hasErrors('email')}"></p>
                        </div>
                        <div class="form-group mb-3">
                            <label class="form-label">Password:</label>
                            <input class="form-control" id="password" name="password" placeholder="Enter password" th:field="*{password}" type="password"/>
                            <p class="text-danger" th:errors="*{password}" th:if="${#fields.hasErrors('password')}"></p>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-primary" type="submit">Register</button>
                            <span>Already registered? <a th:href="@{/login}">Login here</a></span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!--bootstrap-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous"></script>
<!--end-bootstrap-->
<!--javascript-->
<script src="main.js"></script>
<!--end-javascript-->
</body>
</html>
