package com.example.JUnitTest;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SushiService {
    private final SushiDao sushiDao;

    // Springが自動でSushiDaoを注入してくれるようになります
    public SushiService(SushiDao sushiDao) {
        this.sushiDao = sushiDao;
    }

    public void printSushiListWithTax() {
        List<Sushi> list = sushiDao.findAll();
        if (list == null) return;

        for (int i = 0; i < list.size(); i++) {
            Sushi s = list.get(i);
            int taxPrice = (int) (s.getPrice() * 1.1);
            System.out.printf("%s :%d円 (%d円) ", s.getName(), s.getPrice(), taxPrice);
            if ((i + 1) % 3 == 0) System.out.println();
        }
        System.out.println();
    }
    
    public List<Sushi> getCalculatedList() {
        List<Sushi> list = sushiDao.findAll();
        if (list != null) {
            for (Sushi s : list) {
                s.setTaxPrice((int)(s.getPrice() * 1.1)); // ここで税込を計算
            }
        }
        return list;
    }
}