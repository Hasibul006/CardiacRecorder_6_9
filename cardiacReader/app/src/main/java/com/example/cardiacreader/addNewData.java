package com.example.cardiacreader;

import android.icu.text.AlphabeticIndex;

import java.util.ArrayList;
import java.util.List;


public class addNewData {
    public List<Records> records = new ArrayList<>(); //a list of type "Medical Report Model" is declared

    /**
     * this method is used to add any new data
     * if data already exists,it will throw an exception
     * @param data a new record
     */
    public void addData(Records data)
    {
        if(records.contains(data))
        {
            throw new IllegalArgumentException();
        }
        records.add(data);
    }

    /**
     * this method returns an instance of sorted record list
     * sort is based on first attribute by default
     * @return a list of data
     */

    public List<Records> getData()
    {
        return records;
    }

    public Records getData(int x)
    {
        return records.get(x);
    }

    /**
     * this method is used for deleting a particular data
     * if the data doesnt exist,it will throw an exception
     * @param data a data that need to be deleted
     */
    public void deleteData(Records data)
    {
        List<Records> dataList = records;
        if(dataList.contains(data)){
            records.remove(data);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this method returns the size of list
     * @return int
     */
    public int countData()
    {
        return records.size();
    }
}
