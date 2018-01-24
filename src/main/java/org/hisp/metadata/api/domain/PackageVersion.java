package org.hisp.metadata.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

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
        setAutoFields();
    }

    @JsonProperty
    public String getVersionUrl()
    {
        return versionUrl;
    }

    public void setVersionUrl( String url )
    {
        this.versionUrl = url;
    }

    @JsonProperty
    public String getVersion()
    {
        return version;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }

    @JsonIgnore
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
