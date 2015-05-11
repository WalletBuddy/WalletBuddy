package com.dalin.my.walletbuddy.data;
import com.parse.ParseClassName;
import com.parse.ParseObject;


@ParseClassName("BudgetData")

public class BudgetData extends ParseObject
{
    public BudgetData()
    {

    }

    public String getCategory()
    {
        return getString("category");
    }

    public void setCategory(String category)
    {
        put("category", category);
    }

    public double getCost()
    {
        return getDouble("expenses");
    }

    public void setCost(double expenses)
    {
        put("expenses", expenses);
    }

    public double getBudget()
    {
        return getDouble("Budget");
    }

    public void setBudget(double startingBudget)
    {
        put("Budget", startingBudget);
    }
}