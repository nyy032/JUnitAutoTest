package com.example.JUnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class SushiServiceApplicationTests {

    @Autowired
    private SushiService service;

    @MockitoBean
    private SushiDao mockDao;

    @Test
    @DisplayName("8個の寿司データの表示と税込金額の全件自動検証")
    void testSushiFullProcess() {
        // 1. お気に入りの8個のデータを準備
        List<Sushi> fakeDb = Arrays.asList(
            new Sushi("マグロ", 100), new Sushi("サーモン", 120), new Sushi("エビ", 100),
            new Sushi("イカ", 80), new Sushi("タコ", 80), new Sushi("いくら", 250),
            new Sushi("うに", 300), new Sushi("アナゴ", 150)
        );

        // 2. モックに設定
        when(mockDao.findAll()).thenReturn(fakeDb);
        
        // 3. 表示の実行（コンソールで3個ずつの改行を確認）
        System.out.println("--- テスト実行：コンソール出力開始 ---");
        service.printSushiListWithTax();
        System.out.println("--- テスト実行：コンソール出力終了 ---");

        // 4. 自動検証の実行（計算済みリストを取得してチェック）
        List<Sushi> results = service.getCalculatedList();

        // 5. 【全自動検証】8個すべての金額が正しいかJUnitが判定
        assertAll("全8件の税込価格・件数チェック",
            () -> assertEquals(8, results.size(), "データ件数が一致しません"),
            () -> assertEquals(110, results.get(0).getTaxPrice(), "マグロの価格ミス"),
            () -> assertEquals(132, results.get(1).getTaxPrice(), "サーモンの価格ミス"),
            () -> assertEquals(110, results.get(2).getTaxPrice(), "エビの価格ミス"),
            () -> assertEquals(88,  results.get(3).getTaxPrice(), "イカの価格ミス"),
            () -> assertEquals(88,  results.get(4).getTaxPrice(), "タコの価格ミス"),
            () -> assertEquals(275, results.get(5).getTaxPrice(), "いくらの価格ミス"),
            () -> assertEquals(330, results.get(6).getTaxPrice(), "うにの価格ミス"),
            () -> assertEquals(165, results.get(7).getTaxPrice(), "アナゴの価格ミス")
        );
    } 
}