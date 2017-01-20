package com.a5corp.currencyconverter.data;

class Currency {
    private String currency_code;
    private float rate;
    private String code;
    private String name;

    public String getCurrencyCode() {
        return currency_code;
    }

    public float getRate() {
        return rate;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCurrencyCode(String currency_code) {
        this.currency_code = currency_code;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setName(String name) {
        this.name = name;
    }
}