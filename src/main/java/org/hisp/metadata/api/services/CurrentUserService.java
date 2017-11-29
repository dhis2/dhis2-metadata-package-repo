package org.hisp.metadata.api.services;

import java.util.Set;

/**
 * Created by zubair on 20.03.17.
 */
public interface CurrentUserService
{
    String getCurrentUserId();

    boolean isManager();

    Set<String> getUserAuthorities();

    String getCurrentUserEmail();

    String getCurrentUserGivenName();

    String getCurrentUserFamilyName();
}
