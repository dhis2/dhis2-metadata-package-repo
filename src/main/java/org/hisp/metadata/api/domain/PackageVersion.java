package org.hisp.metadata.api.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.Entity;

/**
 * Created by zubair on 03.02.17.
 */
public class PackageVersion extends BaseIdentifiableObject
{
    private String version;

    private String versionUrl;

    private MetaDataPackage metaDataPackage;

    public PackageVersion()
    {
    }

    public String getVersionUrl()
    {
        return versionUrl;
    }

    public void setVersionUrl( String url )
    {
        this.versionUrl = versionUrl;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }

    public MetaDataPackage getMetaDataPackage()
    {
        return metaDataPackage;
    }

    public void setMetaDataPackage( MetaDataPackage metaDataPackage )
    {
        this.metaDataPackage = metaDataPackage;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
                .add( "version", this.version )
                .add( "version url", this.versionUrl )
                .toString();
    }
}
