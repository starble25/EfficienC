package com.app.dto.todolist;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoListRemove {
	int todoListId;
	String loginUserId;
	public static List<TodoList> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}