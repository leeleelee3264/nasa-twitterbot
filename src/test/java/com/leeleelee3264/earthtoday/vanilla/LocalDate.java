package com.leeleelee3264.earthtoday.vanilla;

public class LocalDate {
    public static void main(String[] args)  {

        java.time.LocalDate today = java.time.LocalDate.now();
        int month = today.getMonthValue();

        System.out.println(month);
    }

}
