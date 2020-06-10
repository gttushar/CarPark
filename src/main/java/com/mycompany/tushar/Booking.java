package com.mycompany.tushar;
import java.util.Date;
import java.sql.*;
public class Booking 
{
    public String owner, customer, phone, garage, model, paid;
    public int number,slotno, cost;
    public Date start, end;

    public Booking(int number, String owner, String customer, String phone, String garage, String model, int slotno, Date start, Date end, int cost, String paid) {
        this.number = number;
        this.owner = owner;
        this.customer = customer;
        this.phone = phone;
        this.garage = garage;
        this.model = model;
        this.paid = paid;
        this.slotno = slotno;
        this.cost = cost;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Booking{" + "owner=" + owner + ", customer=" + customer + ", phone=" + phone + ", garage=" + garage + ", model=" + model + ", paid=" + paid + ", number=" + number + ", slotno=" + slotno + ", cost=" + cost + ", start=" + start + ", end=" + end + '}';
    }
    
}
