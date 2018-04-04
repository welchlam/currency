package com.welch;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pdclwc on 30/03/2018.
 */
public class CurrencyValidatorTest {
    @Test
    public void isNumberTestWithValidNumber(){
        String input = "123";
        boolean result = CurrencyValidator.isNumber(input);
        Assert.assertTrue(result);
    }

    @Test
    public void isNumberTestWithInvalidNumber(){
        String input = "ABC";
        boolean result = CurrencyValidator.isNumber(input);
        Assert.assertFalse(result);
    }

    @Test
    public void validateTestWithValidInput(){
        String input = "USD 1000";
        String result = CurrencyValidator.validate(input);
        Assert.assertNull(result);
    }

    @Test
    public void validateTestWithInValidInput(){
        String input = "USD 1000 A";
        String result = CurrencyValidator.validate(input);
        Assert.assertNotNull(result);

        input = "ABC ABC";
        result = CurrencyValidator.validate(input);
        Assert.assertNotNull(result);

        input = "ABCD 1234";
        result = CurrencyValidator.validate(input);
        Assert.assertNotNull(result);
    }
}
