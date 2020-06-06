package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;

    @Test
    public void fetchTrelloBoardsTest() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();
        //Then
        Assert.assertNotNull(trelloBoardDtos);
        Assert.assertEquals(0, trelloBoardDtos.size());
    }
}
