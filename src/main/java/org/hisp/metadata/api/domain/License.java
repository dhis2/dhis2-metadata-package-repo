package org.hisp.metadata.api.domain;

/**
 * Created by zubair on 03.02.17.
 */
public class License extends BaseIdentifiableObject
{
    private String licenseName;

    private String licenseUrl;

    public License()
    {
        setAutoFields();
    }

    public License( String licenseName, String licenseUrl )
    {
        this.licenseName = licenseName;
        this.licenseUrl = licenseUrl;
    }

    public String getLicenseName()
    {
        return licenseName;
    }

    public void setLicenseName( String licenseName )
    {
        this.licenseName = licenseName;
    }

    public String getLicenseUrl()
    {
        return licenseUrl;
    }

    public void setLicenseUrl( String licenseUrl )
    {
        this.licenseUrl = licenseUrl;
    }
}
