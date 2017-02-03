package org.hisp.metadata.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hisp.metadata.utils.CodeUtils;

import java.util.Date;

/**
 * Created by zubair on 03.02.17.
 */
public class BaseIdentifiableObject
        implements IdentifiableObject
{
    protected int id;

    protected String uid;

    protected Date created;

    protected Date lastUpdated;

    @Override
    @JsonIgnore
    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    @Override
    @JsonProperty( value = "id" )
    public String getUid()
    {
        return uid;
    }

    public void setUid( String uid )
    {
        this.uid = uid;
    }

    @Override
    @JsonProperty
    public Date getCreated()
    {
        return created;
    }

    public void setCreated( Date created )
    {
        this.created = created;
    }

    @Override
    @JsonProperty
    public Date getLastUpdated()
    {
        return this.lastUpdated;
    }

    @Override
    public void setLastUpdated( Date updated )
    {
        this.lastUpdated = updated;
    }

    @Override
    public int hashCode()
    {
        int result = getUid() != null ? getUid().hashCode() : 0;

        return result;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }

        if ( o == null )
        {
            return false;
        }

        if ( !getClass().isAssignableFrom( o.getClass() ) )
        {
            return false;
        }

        final BaseIdentifiableObject other = (BaseIdentifiableObject) o;

        if ( getUid() != null ? !getUid().equals( other.getUid() ) : other.getUid() != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return "[id='" + id + "', uid='" + uid + "', created='" + created + "']";
    }

    public void setAutoFields()
    {
        if ( getUid() == null )
        {
            setUid( CodeUtils.generateCode() );
        }

        if ( getCreated() == null )
        {
            setCreated( new Date() );
        }

        setLastUpdated( new Date() );
    }
}