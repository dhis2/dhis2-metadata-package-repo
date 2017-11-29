package org.hisp.metadata.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zubair on 03.02.17.
 */
public class Icon
{
    private String iconId;

    private String url;

    @JsonProperty
    public String getIconId()
    {
        return iconId;
    }

    public void setIconId( String iconId )
    {
        this.iconId = iconId;
    }

    @JsonProperty
    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }
}
