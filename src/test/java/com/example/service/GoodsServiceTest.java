package com.example.service;

import com.example.dao.GoodsDao;
import com.example.model.Goods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class GoodsServiceTest {

    @Mock
    private GoodsDao goodsDao;

    @InjectMocks
    private GoodsService goodsService;

    @Test
    @DisplayName("Mockito隔离测试：根据分类ID分页获取商品列表")
    void testSelectGoodsByTypeID() throws SQLException {
        int testTypeID = 1;
        int testPageNumber = 1;
        int testPageSize = 10;

        List<Goods> mockData = new ArrayList<>();
        mockData.add(new Goods(1, "测试蛋糕A"));
        mockData.add(new Goods(2, "测试蛋糕B"));

        Mockito.when(goodsDao.selectGoodsByTypeID(testTypeID, testPageNumber, testPageSize))
                .thenReturn(mockData);

        List<Goods> result = goodsService.selectGoodsByTypeID(testTypeID, testPageNumber, testPageSize);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("测试蛋糕A", result.get(0).getName());

        Mockito.verify(goodsDao, Mockito.times(1))
                .selectGoodsByTypeID(testTypeID, testPageNumber, testPageSize);
    }
}