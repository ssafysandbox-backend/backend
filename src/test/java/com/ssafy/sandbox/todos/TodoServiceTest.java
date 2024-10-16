package com.ssafy.sandbox.todos;

import com.ssafy.sandbox.todo.dto.TodoDTO;
import com.ssafy.sandbox.todo.repository.TodoRepository;
import com.ssafy.sandbox.todo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    public void setUp() {
        todoRepository.deleteAll();
        IntStream.rangeClosed(1, 100).forEach(i -> {
            todoService.createTodo("Todo item " + i);
        });
    }

    @Test
    public void testGetSomeTodosWithOffset() {
        // Test first page
        List<TodoDTO> firstPage = todoService.getSomeTodosWithOffset(0, 10);
        assertEquals(10, firstPage.size());
        assertEquals("Todo item 1", firstPage.get(0).getContent());
        assertEquals("Todo item 10", firstPage.get(9).getContent());

        // Test second page
        List<TodoDTO> secondPage = todoService.getSomeTodosWithOffset(1, 10);
        assertEquals(10, secondPage.size());
        assertEquals("Todo item 11", secondPage.get(0).getContent());
        assertEquals("Todo item 20", secondPage.get(9).getContent());

        // Test last page (assuming 100 items total)
        List<TodoDTO> lastPage = todoService.getSomeTodosWithOffset(9, 10);
        assertEquals(10, lastPage.size());
        assertEquals("Todo item 91", lastPage.get(0).getContent());
        assertEquals("Todo item 100", lastPage.get(9).getContent());

        // Test beyond last page
        List<TodoDTO> beyondLastPage = todoService.getSomeTodosWithOffset(10, 10);
        assertTrue(beyondLastPage.isEmpty());
    }

    @Test
    public void testGetSomeTodosWithCursor() {
        // Test first page
        List<TodoDTO> firstPage = todoService.getSomeTodosWithCursor(0L, 10);
        assertNotNull(firstPage);
        assertEquals(10, firstPage.size());
        assertEquals("Todo item 1", firstPage.get(0).getContent());
        assertEquals("Todo item 10", firstPage.get(9).getContent());

        // Test next page
        Long nextCursor = firstPage.get(9).getId();
        List<TodoDTO> secondPage = todoService.getSomeTodosWithCursor(nextCursor, 10);
        assertNotNull(secondPage);
        assertEquals(10, secondPage.size());
        assertEquals("Todo item 11", secondPage.get(0).getContent());
        assertEquals("Todo item 20", secondPage.get(9).getContent());

        // Test last page
        Long lastCursor = (long) (todoRepository.findMaxId().intValue() - 10);
        List<TodoDTO> lastPage = todoService.getSomeTodosWithCursor(lastCursor, 10);
        assertNotNull(lastPage);
        assertEquals(10, lastPage.size());
        assertEquals("Todo item 91", lastPage.get(0).getContent());
        assertEquals("Todo item 100", lastPage.get(9).getContent());

        // Test beyond last page
        List<TodoDTO> beyondLastPage = todoService.getSomeTodosWithCursor(todoRepository.findMaxId(), 10);
        assertNull(beyondLastPage);
    }
}