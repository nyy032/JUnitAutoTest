package com.example.JUnitTest;
public class TaxDao {
    // 本来はここでDBに接続しますが、今は空っぽでOK
    public int getLatestRate() {
        // 本物のDBがない環境では、ここは正しく動きません
        return 0; 
    }
}