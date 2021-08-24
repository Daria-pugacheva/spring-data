package ru.gb.pugacheva.spring7.springdata.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MarketError {
    private String message;

    public MarketError (String message){
        this.message = message;

    }

}
