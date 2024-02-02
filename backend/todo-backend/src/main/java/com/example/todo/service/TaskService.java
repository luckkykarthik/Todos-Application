package com.example.todo.service;

import java.util.List;

import com.example.todo.payload.taskDto;

public interface TaskService {
	public taskDto createTask(long userid,taskDto task,long id);
	
	public List<taskDto> getAllTasks(long userid);
	
	public taskDto getTask(long userid,long id);
	
	public taskDto updateTask(long userid,long id,taskDto taskdto);
	
	public void deleteTask(long userid,long id);
	

}
