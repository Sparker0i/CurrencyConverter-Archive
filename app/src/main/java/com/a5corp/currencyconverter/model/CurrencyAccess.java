package com.a5corp.currencyconverter.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CurrencyAccess {

    @Insert void insertCurrency(CurrencyTable currency);
    @Insert void insertMultipleCurrencies (List<CurrencyTable> currencies);
    @Query("SELECT * FROM CurrencyTable WHERE id= :id") CurrencyTable getCurrency(int id);
    @Update void updateCurrency(CurrencyTable currency);
    @Delete void deleteCurrency(CurrencyTable currency);
}
