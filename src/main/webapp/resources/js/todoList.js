/**
 * 
 */
/*$(document).ready(function() {
	// 오늘 할일 전체 조회하는 ajax
	fetchTodoList();*/


	console.log('todoList.js console log');

	// 체크리스트 저장 버튼 이벤트 핸들러
	$('#addTodoButton').on('click', addTodo);

	// 체크리스트 입력값을 Enter 키로 추가
	$('#todoInput').on('keypress', function(event) {
		if (event.key === 'Enter') {
			$('#addTodoButton').click(); // Enter 누르면 button 입력한 것과 동일하게 처리
		}
	});

	// 전체 체크리스트 조회
	function fetchTodoList() {
		$.ajax({
			url: "/todoList/viewAll",
			type: "POST",
			data: { loginUserId: loginUserId },
			dataType: "json",
			success: function(response) {

				let resultCode = response.header.resultCode;
				let resultMsg = response.header.resultMessage;

				if (response && response.header && resultCode == "00") {
					const todoList = $('#todoList');
					todoList.empty(); // 기존 리스트를 비움

					let responseTodoList = response.body;

					if (responseTodoList != null && responseTodoList.length > 0) {
						responseTodoList.forEach(todo => {
							const li = $('<li>').attr('id', todo.todolistId);
							const checkbox = $('<input>', { type: 'checkbox' }).prop('checked', todo.todolistStatus === 'cmp');

							if (checkbox.prop('checked')) li.addClass('checked');

							checkbox.on('change', function() {
								handleCheckboxChange(this, li);
							});

							const textNode = document.createTextNode(todo.todolistContents);
							const removeButton = $('<button>').text('x').addClass('remove-btn').on('click', function() {
								if (confirm("삭제하시겠습니까?")) {
									removeTodoItem(li);
								}
							});

							li.append(checkbox, textNode, removeButton);
							todoList.append(li);
							$('#todoInput').val('');
							console.log(`/todoList/remove ${resultCode} ${resultMsg}`);
						});
					}


				} else if (response && response.header && resultCode == "01") {
					const todoList = $('#todoList');
					todoList.empty(); // 기존 리스트를 비움

				} else {
					console.log(`/todoList/viewAll: ${resultCode} ${resultMsg}`);
				}

			},
			error: function(error) {
				console.log("Error: " + error);
			}
		});
	}

	// 체크박스 상태 변경 처리
	function handleCheckboxChange(checkbox, li) {
		const status = $(checkbox).prop('checked') ? 'cmp' : 'reg';
		$.ajax({
			url: "/todoList/checkedOn",
			type: "POST",
			data: JSON.stringify({
				loginUserId: loginUserId,
				todoListId: li.attr('id'),
				todoListStatus: status
			}),
			contentType: 'application/json; charset=utf-8',
			dataType: "json",
			success: function(response) {

				let resultCode = response.header.resultCode;
				let resultMsg = response.header.resultMessage;


				if (response && response.header && resultCode == "00") {
					li.toggleClass('checked', $(checkbox).prop('checked'));
					console.log(`/todoList/checkedOn ${resultCode} ${resultMsg}`);
				} else {
					console.log(`/todoList/checkedOn ${resultCode} ${resultMsg}`);
				}




			},
			error: function() {
				alert("체크박스 상태 변경 에러 발생");
			}
		});
	}

	// 체크리스트 추가
	function addTodo() {
		const todoText = $('#todoInput').val().trim();
		if (todoText === '') {
			alert('할 일을 입력해주세용~');
			return;
		}
		//추가만하고 전체 조회하는 함수를 호출
		$.ajax({
			url: "/todoList/register",
			type: "POST",
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify({
				loginUserId: loginUserId,
				todoText: todoText
			}),
			dataType: "json",
			success: function(response) {

				let resultCode = response.header.resultCode;
				let resultMsg = response.header.resultMessage;

				if (response && response.header && resultCode == "00") {
					fetchTodoList();
					console.log(`/todoList/register ${resultCode} ${resultMsg}`);
				} else {
					console.log(`/todoList/register ${resultCode} ${resultMsg}`);
				}

			},
			error: function() {
				alert("에러 발생");
			}
		});
	}

	// 체크리스트 삭제
	function removeTodoItem(li) {

		$.ajax({
			url: "/todoList/remove",
			type: "POST",
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify({
				todoListId: li.attr('id'),
				loginUserId: loginUserId
			}),
			dataType: "json",
			success: function(response) {
				let resultCode = response.header.resultCode;
				let resultMsg = response.header.resultMessage;

				if (response && response.header && resultCode == "00") {
					li.remove(); // 체크리스트 삭제
					console.log(`/todoList/remove ${resultCode} ${resultMsg}`);
				} else {
					console.log(`/todoList/remove ${resultCode} ${resultMsg}`);
				}


			},
			error: function(error) {
				console.log("Error: " + error);
			}

		});

	}