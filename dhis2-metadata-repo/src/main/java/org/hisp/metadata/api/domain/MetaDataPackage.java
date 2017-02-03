package org.hisp.metadata.api.domain;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 * Created by zubair on 03.02.17.
 */
public class MetaDataPackage extends BaseIdentifiableObject
{
    private String name;

    private String organization;

    private String description;

    private String url;

    private Map<String, String> icons = Maps.newHashMap();

    private Map<String, String> license = Maps.newHashMap();

    private Set<String> tags = Sets.newHashSet();

    private Set<PackageVersion> versions = Sets.newHashSet();

    public MetaDataPackage()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization( String organization )
    {
        this.organization = organization;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }

    public Map<String, String> getIcons()
    {
        return icons;
    }

    public void setIcons( Map<String, String> icons )
    {
        this.icons = icons;
    }

    public Map<String, String> getLicense()
    {
        return license;
    }

    public void setLicense( Map<String, String> license )
    {
        this.license = license;
    }

    public Set<String> getTags()
    {
        return tags;
    }

    public void setTags( Set<String> tags )
    {
        this.tags = tags;
    }

    public Set<PackageVersion> getVersions()
    {
        return versions;
    }

    public void setVersions( Set<PackageVersion> versions )
    {
        this.versions = versions;
    }
}
