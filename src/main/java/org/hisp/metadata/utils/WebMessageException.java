package org.hisp.metadata.utils;

import org.hisp.metadata.api.support.WebMessage;

/**
 * Created by zubair on 14.12.16.
 */
public class WebMessageException extends Exception
{
    private WebMessage webMessage;

    public WebMessageException( WebMessage webMessage )
    {
        this.webMessage = webMessage;
    }

    public WebMessage getWebMessage()
    {
        return webMessage;
    }
}

