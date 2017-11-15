package org.hisp.metadata.api.domain;

import java.util.Date;

/**
 * Created by zubair on 03.02.17.
 */
public interface IdentifiableObject
{
    int getId();

    void setId( int id );

    String getUid();

    void setUid( String uid );

    Date getCreated();

    void setCreated( Date created );

    Date getLastUpdated();

    void setLastUpdated( Date updated );
}
