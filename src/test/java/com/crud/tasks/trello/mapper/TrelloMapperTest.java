package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1a", "testList", false);
        List<TrelloListDto> dtoList = Arrays.asList(trelloListDto);
        //When
        List<TrelloList> listToMap = trelloMapper.mapToList(dtoList);
        //Then
        Assert.assertEquals(dtoList.get(0).getId(), listToMap.get(0).getId());
        Assert.assertEquals(dtoList.get(0).getName(), listToMap.get(0).getName());
        Assert.assertEquals(dtoList.get(0).isClosed(), listToMap.get(0).isClosed());
    }

    @Test
    public void mapToDtoListTest() {
        //Given
        TrelloList trelloList = new TrelloList("1a", "testList", false);
        List<TrelloList> dtoList = Arrays.asList(trelloList);
        //When
        List<TrelloListDto> listToMap = trelloMapper.mapToListDto(dtoList);
        //Then
        Assert.assertEquals(dtoList.get(0).getId(), listToMap.get(0).getId());
        Assert.assertEquals(dtoList.get(0).getName(), listToMap.get(0).getName());
        Assert.assertEquals(dtoList.get(0).isClosed(), listToMap.get(0).isClosed());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test card", "test description", "test pos", "123");
        //When
        TrelloCard cardToMap = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals(trelloCardDto.getName(), cardToMap.getName());
        Assert.assertEquals(trelloCardDto.getDescription(), cardToMap.getDescription());
        Assert.assertEquals(trelloCardDto.getPos(), cardToMap.getPos());
        Assert.assertEquals(trelloCardDto.getListId(), cardToMap.getListId());

    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test card", "test description", "test pos", "123");
        //When
        TrelloCardDto cardToMap = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals(trelloCard.getName(), cardToMap.getName());
        Assert.assertEquals(trelloCard.getDescription(), cardToMap.getDescription());
        Assert.assertEquals(trelloCard.getPos(), cardToMap.getPos());
        Assert.assertEquals(trelloCard.getListId(), cardToMap.getListId());

    }

    @Test
    public void mapToBoardTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("213", "test list", false);
        List<TrelloListDto> dtoList = Arrays.asList(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("test board", "123", dtoList);
        List<TrelloBoardDto> dtoBoard = Arrays.asList(trelloBoardDto);
        //When
        List<TrelloBoard> listToBoard = trelloMapper.mapToBoard(dtoBoard);
        //Then
        Assert.assertEquals(dtoBoard.get(0).getName(), listToBoard.get(0).getName());
        Assert.assertEquals(dtoBoard.get(0).getId(), listToBoard.get(0).getId());
        Assert.assertEquals(dtoBoard.get(0).getLists().get(0).getId(), listToBoard.get(0).getLists().get(0).getId());
        Assert.assertEquals(dtoBoard.get(0).getLists().get(0).getName(), listToBoard.get(0).getLists().get(0).getName());
        Assert.assertEquals(dtoBoard.get(0).getLists().get(0).isClosed(), listToBoard.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void mapToBoardDtoTest() {
        //Given
        TrelloList trelloList = new TrelloList("213", "test list", false);
        List<TrelloList> dtoList = Arrays.asList(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("test board", "123", dtoList);
        List<TrelloBoard> dtoBoard = Arrays.asList(trelloBoard);
        //When
        List<TrelloBoardDto> listToBoard = trelloMapper.mapToBoardDto(dtoBoard);
        //Then
        Assert.assertEquals(dtoBoard.get(0).getName(), listToBoard.get(0).getName());
        Assert.assertEquals(dtoBoard.get(0).getId(), listToBoard.get(0).getId());
        Assert.assertEquals(dtoBoard.get(0).getLists().get(0).getId(), listToBoard.get(0).getLists().get(0).getId());
        Assert.assertEquals(dtoBoard.get(0).getLists().get(0).getName(), listToBoard.get(0).getLists().get(0).getName());
        Assert.assertEquals(dtoBoard.get(0).getLists().get(0).isClosed(), listToBoard.get(0).getLists().get(0).isClosed());
    }
}
