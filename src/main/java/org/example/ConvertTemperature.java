package org.example;

/*
    You are given a non-negative floating point number rounded to two decimal places celsius,
    that denotes the temperature in Celsius.
    You should convert Celsius into Kelvin and Fahrenheit
    and return it as an array ans = [kelvin, fahrenheit].
    Return the array ans.

    Note that:
        Kelvin = Celsius + 273.15
        Fahrenheit = Celsius * 1.80 + 32.00
 */
class ConvertTemperature {

    private static final double ONE_KELVIN = 273.15;
    private static final int FAHRENHEIT_FREEZING_WATER = 32;
    private static final double DIFF_RANGE_CELSIUS_FAHRENHEIT = 1.8;

    public double[] convertTemperature(final double celsius) {
        return new double[]{convertToKelvins(celsius), convertToFahrenheit(celsius)};
    }

    private double convertToKelvins(final double celsius) {
        return celsius + ONE_KELVIN;
    }

    private double convertToFahrenheit(final double celsius) {
        return celsius * DIFF_RANGE_CELSIUS_FAHRENHEIT + FAHRENHEIT_FREEZING_WATER;
    }
}
