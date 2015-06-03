package com.dalin.mywallet.walletbuddy.data;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("CategoryExpenses")
public class CategoryExpenses extends ParseObject
{
    public CategoryExpenses()
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

    public String getPlace()
    {
        return getString("Name");
    }

    public void setPlace(String name)
    {
        put("Name", name);
    }

    public double getCost()
    {
        return getDouble("Cost");
    }

    public void setCost(Double cost)
    {
        put("Cost", cost);
    }

    public void setUser(ParseUser currentUser)
    {
        put("user", currentUser);
    }

    public String getUser()
    {
        return getString("user");
    }




}

