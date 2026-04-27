package com.example.JUnitTest;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SushiDao {
    public List<Sushi> findAll() {
        return null; // モックを使うので中身はこれでOK
    }
}