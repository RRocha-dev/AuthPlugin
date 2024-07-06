package com.rickrocha.utils.impl;

import com.rickrocha.utils.interfaces.MessageUtils;

public class MessageUtilsImpl implements MessageUtils {

    @Override
    public String convertCharacterToConsole(String message) {
        if (message != null && message != "")
            return message.replace("&", "§");
        else
            return "";
    }

}
