/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tushar;

import java.util.List;

/**
 *
 * @author Tushar
 */
public class lol {
    public static void main(String args[])
    {
        try{List<City.Garage> garages= new City().searchgarages(0);
        for(City.Garage garage:garages)
        {
            System.out.println(garage);
        }
        }catch(Exception e){e.printStackTrace();}
    }
}
