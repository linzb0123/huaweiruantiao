package com.huawei;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:\\Users\\lzb\\Desktop\\huawei\\复赛\\huaweiruantiao\\logs\\CodeCraft-2019.log"));
        Scanner sc2 = new Scanner(new File("C:\\Users\\lzb\\Desktop\\huawei\\复赛\\fusai-ptq\\logs\\CodeCraft-2019.log"));
        sc.nextLine();
        int i=1;
        while(true){
            String str1 = sc.nextLine();
            String str2 = sc2.nextLine();
            if(!str1.equals(str2)){
                System.out.println(i);
                break;
            }
            i++;
        }
        
        
    }
}
