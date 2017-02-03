package org.hisp.metadata.api.domain;

/**
 * Created by zubair on 03.02.17.
 */
public class License extends BaseIdentifiableObject
{
    private String name;

    private String url;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }
}
