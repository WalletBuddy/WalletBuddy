package com.dalin.my.walletbuddy.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;


@ParseClassName("CategoryData")

public class CategoryData extends ParseObject
{
    public CategoryData()
    {

    }
    public String getCategory()
    {
        return getString("Category");
    }

    public void setCategory(String category)
    {
        put("Category", category);
    }


    public void setUser(ParseUser currentUser)
    {
        put("user", currentUser);
    }

    public String getUser()
    {
        return getString("user");
    }

    public double getTotalCost()
    {
        return getDouble("Total");
    }
    public void setTotalCost(double cost)
    {
        put("Total", cost);
    }

    public int getTotalTransactions()
    {
        return getInt("Transactions");
    }

    public void setTotalTransactions(int number)
    {
        put("Transactions", number);
    }
}
