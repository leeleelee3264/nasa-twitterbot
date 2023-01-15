package com.leeleelee3264.nasabot.vanilla;

import com.leeleelee3264.nasabot.global.exception.ShellException;

public class ParentException {

    public static void throwChildException() {

        throw new ShellException.FailedCommand("This is child Exception");
    }


    public static void main(String[] args) {

        try {
            throwChildException();
            System.out.println("Can not Catch it!");
        } catch (ShellException e) {
            System.out.println("Catch it!");
        }
    }
}
