package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    private static TaskDto taskDto;
    private static Task task;
    private static Gson gson;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService dbService;
    @MockBean
    private TaskMapper taskMapper;


    @Test
    public void shouldFetchEmptyTasksList() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(new ArrayList<>())).thenReturn(taskDtos);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // or isOk()
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTasksList() throws  Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(new TaskDto(1L, "test task", "test content"));
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtos);
        //when & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test task")))
                .andExpect(jsonPath("$[0].content", is("test content")));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        taskDto = new TaskDto(1L, "test title", "test task");
        task = new Task(1L, "test title", "test task");
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(dbService.getTaskById(1L)).thenReturn(java.util.Optional.of(task));
        //When & Then
        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("taskId", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test task")));

    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        taskDto = new TaskDto(1L, "test title", "test task");
        task = new Task(1L, "test title", "test task");
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);

        gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent)
                .param("taskId", "1"))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        taskDto = new TaskDto(1L, "test title", "test task");
        task = new Task(1L, "test title", "test task");
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);

        gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test task")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        task = new Task(1L, "test title", "test task");
        when(dbService.getTaskById(1L)).thenReturn(Optional.of(task));

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("taskId", "1"))
                .andExpect(status().is(200));

    }


}