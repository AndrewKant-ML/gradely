package it.uniroma2.dicii.ispw.gradely;

import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.io.IOException;
import java.util.Properties;

public class PropertiesHandler {

    private static PropertiesHandler instance = null;
    private Properties properties;

    private PropertiesHandler() throws ResourceNotFoundException {
        try {
            properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(".properties"));
        } catch (IOException | NullPointerException e) {
            throw new ResourceNotFoundException(ExceptionMessagesEnum.RESOURCE_NOT_FOUND.message, e);
        }
    }

    public static PropertiesHandler getInstance() throws ResourceNotFoundException {
        if (instance == null)
            instance = new PropertiesHandler();
        return instance;
    }

    public String getProperty(String propertyName) throws PropertyException {
        String propertyValue = properties.getProperty(propertyName);
        if (propertyValue != null)
            return propertyValue;
        throw new PropertyException(ExceptionMessagesEnum.UNEXPECTED_PROPERTY_NAME.message);
    }
}
