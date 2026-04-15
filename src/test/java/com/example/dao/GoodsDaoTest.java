package com.example.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.stream.Stream;


class GoodsDaoTest {

    static Stream<Arguments> goodsCountProvider() {
        return Stream.of(

                Arguments.of(0, 166),  // 实际数据库中有 166 条
                Arguments.of(11, 2),   // 实际数据库中有 2 条
                Arguments.of(2, 113)   // 实际数据库中有 113 条
        );
    }

    @ParameterizedTest
    @MethodSource("goodsCountProvider")
    @DisplayName("测试根据商品分类ID获取商品总数")
    void getCountOfGoodsByTypeID(int typeID, int expectedCount) throws SQLException {
        // 1. 实例化 DAO 对象
        GoodsDao gd = new GoodsDao();

        // 2. 调用被测方法获取实际结果
        int actualCount = gd.getCountOfGoodsByTypeID(typeID);

        // 3. 断言：判断实际查询出的数量是否等于预期数量
        // 如果不相等，测试将失败并显示提示信息
        Assertions.assertEquals(expectedCount, actualCount,
                "当 typeID 为 " + typeID + " 时，查询到的商品数量不正确！");
    }
}