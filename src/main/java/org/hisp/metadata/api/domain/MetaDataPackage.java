package org.hisp.metadata.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by zubair on 03.02.17.
 */
public class MetaDataPackage extends BaseIdentifiableObject
{
    private String name;

    private String organization;

    private String description;

    private License license;

    private PackageStatus status = PackageStatus.PENDING;

    private String owner;

    private Set<String> tags = Sets.newHashSet();

    private Set<Icon> icons = Sets.newHashSet();

    private Set<PackageVersion> versions = Sets.newHashSet();

    private Set<Resource> resources = Sets.newHashSet();

    public MetaDataPackage()
    {
        setAutoFields();
    }

    @JsonProperty
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    @JsonProperty
    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization( String organization )
    {
        this.organization = organization;
    }

    @JsonProperty
    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    @JsonProperty
    public Set<Icon> getIcons()
    {
        return icons;
    }

    public void setIcons( Set<Icon> icons )
    {
        this.icons = icons;
    }

    @JsonProperty
    public License getLicense()
    {
        return license;
    }

    public void setLicense( License license )
    {
        this.license = license;
    }

    @JsonProperty
    public Set<String> getTags()
    {
        return tags;
    }

    public void setTags( Set<String> tags )
    {
        this.tags = tags;
    }

    @JsonProperty
    public Set<Resource> getResources()
    {
        return resources;
    }

    public void setResources( Set<Resource> resources )
    {
        this.resources = resources;
    }

    @JsonProperty
    public Set<PackageVersion> getVersions()
    {
        return versions;
    }

    @JsonProperty
    public PackageStatus getStatus()
    {
        return status;
    }

    public void setStatus( PackageStatus status )
    {
        this.status = status;
    }

    public void setVersions(Set<PackageVersion> versions )
    {
        this.versions = versions;
    }

    @JsonProperty
    public String getOwner()
    {
        return owner;
    }

    public void setOwner( String owner )
    {
        this.owner = owner;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
                .add( "uid", uid )
                .add( "name", this.name )
                .add( "description", this.description )
                .add( "status", this.status )
                .add( "owner", this.owner )
                .add( "organization", this.organization )
                .toString();
    }
}
