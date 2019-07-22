package com.skylaker;

import org.junit.jupiter.api.Test;

/**
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/22 11:28 PM
 */
public class TestService {
    @Test
    public void testException(){
        try{
            int i = 1 / 0;
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("异常后逻辑");
    }
}
