package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getAllTasksTest() {
        //Given
        Task newTask = new Task(1L, "any title", "any content");
        List<Task> tasks = Arrays.asList(newTask);
        when(taskRepository.findAll()).thenReturn(tasks);
        //When
        List<Task> theTasks = dbService.getAllTasks();
        //Then
        Assert.assertNotNull(theTasks);
        Assert.assertEquals(1, theTasks.size());
    }

    @Test
    public void saveTaskTest() {
        //Given
        Task newTask = new Task(1L, "any title", "any content");
        when(taskRepository.save(newTask)).thenReturn(newTask);
        //When
        Task savedTask = dbService.saveTask(newTask);
        //Then
        Assert.assertEquals(newTask.getId(), savedTask.getId());
        Assert.assertEquals(newTask.getTitle(), savedTask.getTitle());
        Assert.assertEquals(newTask.getContent(), savedTask.getContent());
    }

    @Test
    public void deleteTaskTest() {
        //Given
        //When
        dbService.deleteTask(2L);
        //Then
        verify(taskRepository, times(1)).deleteById(2L);
    }
}
