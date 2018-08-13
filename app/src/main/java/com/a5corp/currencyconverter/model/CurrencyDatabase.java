package com.a5corp.currencyconverter.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {CurrencyTable.class} , version = 1)
public abstract class CurrencyDatabase extends RoomDatabase {
    public abstract CurrencyAccess daoAccess();
}
