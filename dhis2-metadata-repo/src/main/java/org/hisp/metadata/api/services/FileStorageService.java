package org.hisp.metadata.api.services;

import org.hisp.metadata.api.domain.FileUploadStatus;
import org.hisp.metadata.utils.WebMessageException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zubair on 28.12.16.
 */
public interface FileStorageService
{
    FileUploadStatus uploadFile(MultipartFile file ) throws WebMessageException;

    void deleteFile( String key);
}
