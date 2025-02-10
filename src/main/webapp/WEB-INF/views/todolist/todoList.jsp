<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link rel="stylesheet"
	href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<link href="/css/todolist.css" rel="stylesheet">

<title>todoList</title>
</head>


<body>
    <div class="swiper-rightslide" id="content-todo">
        <div class="wrapper">
            <header>#Check List</header>
            <div class="input-container">
                <input type="text" id="todoInput" placeholder="할 일을 입력하세요">
                <button id="addTodoButton">
                    <i class="fa-solid fa-check"></i>
                </button>
            </div>
            <ul id="todoList"></ul>
        </div>
    </div>

    <script>
        // 페이지 로드 시 할 일 목록 불러오기
        $(document).ready(function() {
            fetchTodoList();

            // 할 일 목록 조회
            function fetchTodoList() {
                $.ajax({
                    url: '/todoList/viewAll',
                    type: 'GET',
                    success: function(response) {
                        const todoList = response.body; // API 응답에서 할 일 목록 받기
                        if (todoList && todoList.length > 0) {
                            $('#todoList').empty(); // 기존 목록 비우기
                            todoList.forEach(function(todo) {
                                // 목록에 할 일 추가
                                $('#todoList').append(`
                                    <li class="todo-item">
                                        <input type="checkbox" ${todo.status === 'Completed' ? 'checked' : ''} disabled>
                                        ${todo.name}
                                    </li>
                                `);
                            });
                        } else {
                            $('#todoList').append('<li>할 일이 없습니다.</li>');
                        }
                    },
                    error: function() {
                        alert('할 일 목록을 불러오는 데 실패했습니다.');
                    }
                });
            }

            // 할 일 추가 버튼 클릭
            $('#addTodoButton').on('click', function() {
                const todoName = $('#todoInput').val().trim();
                if (todoName) {
                    addTodo(todoName);
                    $('#todoInput').val(''); // 입력 필드 비우기
                }
            });

            // 할 일 추가
            function addTodo(todoName) {
                $.ajax({
                    url: '/todoList/register',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ name: todoName, status: 'To-Do' }), // 상태를 'To-Do'로 설정
                    success: function(response) {
                        fetchTodoList(); // 할 일 추가 후 목록 새로 고침
                    },
                    error: function() {
                        alert('할 일 추가 실패');
                    }
                });
            }
        });
    </script>


<!-- 	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
		integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script> -->

	<script src="/js/todoList.js"></script>


</body>
</html>