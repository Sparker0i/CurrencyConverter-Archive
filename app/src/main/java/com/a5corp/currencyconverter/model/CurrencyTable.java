package com.a5corp.currencyconverter.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class CurrencyTable {
    @NonNull private String currencyName;
    @PrimaryKey @NonNull private String id;

    public CurrencyTable() {
        this.currencyName = "A";
        this.id = "0";
    }

    @NonNull public String getCurrencyName() {
        return currencyName;
    }

    @NonNull public String getId() {
        return id;
    }

    public void setCurrencyName(@NonNull String currencyName) {
        this.currencyName = currencyName;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
}
