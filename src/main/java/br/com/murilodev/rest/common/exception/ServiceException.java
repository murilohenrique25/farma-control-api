package br.com.murilodev.rest.common.exception;

import java.text.MessageFormat;
import java.util.function.Supplier;

public class ServiceException extends Exception{

    public ServiceException(String message, Object... params){ super(MessageFormat.format(message, params));}
    public ServiceException(ServiceException e) {super(e);}
    public ServiceException(Throwable e){super(e);}

    public static Supplier<ServiceException> build(String message, Object... params){
        return () -> new ServiceException(message, params);
    }
}
