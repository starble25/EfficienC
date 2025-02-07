<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle}</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: Arial, sans-serif;
            display: flex;
            height: 100vh;
        }
        .sidebar {
            width: 200px;
            background-color: #f4f4f4;
            padding: 20px;
            border-right: 2px solid #ccc;
        }
        .sidebar h2 {
            margin-bottom: 20px;
        }
        .sidebar ul {
            list-style: none;
            padding: 0;
        }
        .sidebar ul li {
            margin: 10px 0;
        }
        .sidebar ul li a {
            text-decoration: none;
            color: #333;
        }
        .content {
            flex-grow: 1;
            padding: 20px;
        }
        .content h1 {
            margin-bottom: 20px;
        }
        .content-box {
            border: 2px solid #ccc;
            padding: 20px;
            height: 80%;
        }
    </style>
</head>
<body>

    <div class="sidebar">
        <h2>로고</h2>
        <ul>
            <li><a href="/main">메뉴1</a></li>
            <li><a href="/calendar">메뉴2</a></li>
            <li><a href="#">메뉴3</a></li>
        </ul>
    </div>

    <div class="content">
        <h1>${pageTitle}</h1>
        <div class="content-box">
            <p>${content}</p>
        </div>
    </div>

</body>
</html>