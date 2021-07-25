/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Maggie
 */


public class CreditCardType implements Serializable {
    private int id;
    private String shortDesc;
    private String longDesc;
    private Boolean isActive;
    private LocalDateTime dateAdded;
    
    public CreditCardType()
    {
        id=0;
        longDesc="Choose One";
    }
    
   public CreditCardType(int id, String shortDesc, String longDesc, Boolean isActive, LocalDateTime dateAdded)
   {
        this.id = id;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.isActive = isActive;
        this.dateAdded = dateAdded;
       
       
       
   }

    public int getId() {
        return id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

   
    public void setId(int id) {
        this.id = id;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }
    public static CreditCardType getCreditCardType(HttpServletRequest request, int value) {
        //Puts each CreditCardType object into a list which is then saved to the session
        List<CreditCardType> cardTypes = (List<CreditCardType>) request.getSession().getAttribute("cards");

        CreditCardType selectedCard = null;

        for (CreditCardType currentCard : cardTypes) {

            if (currentCard.getId() == value) {
                selectedCard = currentCard;
            }
        }

        return selectedCard;
    }
  
   
}
