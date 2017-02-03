package org.hisp.metadata.api.domain;

/**
 * Created by zubair on 03.02.17.
 */
public class PackageVersion
{
    private String version;

    private String url;

    public PackageVersion()
    {
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }
}
