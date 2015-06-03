package com.dalin.mywallet.walletbuddy.data;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;


@ParseClassName("BudgetData")

public class BudgetData extends ParseObject
{
    public BudgetData()
    {

    }

    public double getBudget()
    {
        return getDouble("Budget");
    }

    public void setBudget(double startingBudget)
    {
        put("Budget", startingBudget);
    }

    public double getRemaining()
    {
        return getDouble("Remaining");
    }
    public void setRemaining(double remainingBudget)
    {
        put("Remaining", remainingBudget);
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
