package com.soshiant.server.facade;

import com.soshiant.common.logger.LoggerUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class FacadeResultHandler {

    private final Logger logger = Logger.getLogger(Facade.class);

    public FacadeResult handleFacadeResult(ProceedingJoinPoint call) {

        try{

            return (FacadeResult) call.proceed();

        }
        catch (Exception exception) {
            logger.error(LoggerUtils.getExceptionLogMessage(exception));
            FacadeResult facadeResult = new FacadeException(exception);
            return facadeResult;
        }
        catch (Throwable throwable) {
            logger.fatal(LoggerUtils.getExceptionLogMessage(throwable));
            FacadeResult facadeResult = new FacadeException(new Exception(throwable.getMessage()));
            return facadeResult;
        }
    }


}

