package com.example.todo.serviceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.exception.APIException;
import com.example.todo.exception.UserNotFound;
import com.example.todo.exception.titleAlreadyExists;
import com.example.todo.model.tasks;
import com.example.todo.model.users;
import com.example.todo.payload.taskDto;
import com.example.todo.repositories.TaskRepo;
import com.example.todo.repositories.UserRepo;
import com.example.todo.service.TaskService;



@Service
public class taskServImp implements TaskService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private TaskRepo taskRepo;
	
	@Override
	public taskDto createTask(long userid, taskDto taskdto,long id) {
		if(userid!=id)
			throw new APIException("You are trying to create task for another user");
		users user=userrepo.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %s not found", userid))
				);
		if (taskRepo.existsByUserAndTitle(user, taskdto.getTitle())) {
	        throw new titleAlreadyExists("Task with the same title already exists for the user");
	    }
		tasks task=modelMapper.map(taskdto, tasks.class);
		task.setUser(user);
		tasks savedtask=taskRepo.save(task);
		return modelMapper.map(savedtask,taskDto.class);

	}

	@Override
	public List<taskDto> getAllTasks(long userid) {
		userrepo.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %s not found", userid))
				);
		List<tasks> tasks=taskRepo.findAllByUserId(userid);
		return tasks.stream().map(task -> modelMapper.map(task,taskDto.class)).collect(Collectors.toList());
	}

	@Override
	public taskDto getTask(long userid, long id) {
		users user=userrepo.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %s not found", userid))
				);
		tasks task=taskRepo.findById(id).orElseThrow(
				() -> new UserNotFound(String.format("Task Id %s not found", id))
				);
		if(user.getId()!=task.getUser().getId()) {
			throw new APIException(String.format("Task Id %d not belongs to User Id %s", id,userid));
		}
		return modelMapper.map(task, taskDto.class);
	}

	@Override
	public void deleteTask(long userid, long id) {
		users user=userrepo.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %s not found", userid))
				);
		tasks task=taskRepo.findById(id).orElseThrow(
				() -> new UserNotFound(String.format("Task Id %s not found", id))
				);
		if(user.getId()!=task.getUser().getId())
			throw new APIException(String.format("Task Id %d not belongs to User Id %s", id,userid));
		taskRepo.delete(task);
		
	}

	@Override
	public taskDto updateTask(long userid, long id,taskDto taskdto) {
		users user=userrepo.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %s not found", userid))
				);
		tasks task=taskRepo.findById(id).orElseThrow(
				() -> new UserNotFound(String.format("Task Id %s not found", id))
				);
		if(user.getId()!=task.getUser().getId())
			throw new APIException(String.format("Task Id %d not belongs to User Id %s", id,userid));
		task.setTitle(taskdto.getTitle());
		task.setDescription(taskdto.getDescription());
		task.setDue_date(taskdto.getDue_date());
		task.setCompleted(taskdto.getCompleted());
		tasks updatedtask = taskRepo.save(task);
		return modelMapper.map(updatedtask, taskDto.class);
	}

}