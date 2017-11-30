package org.hisp.metadata.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hisp.metadata.api.domain.FileUploadStatus;
import org.hisp.metadata.api.services.FileStorageService;
import org.hisp.metadata.utils.WebMessageException;
import org.hisp.metadata.utils.WebMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by zubair on 28.12.16.
 */

@Service
@Transactional
public class AmazonS3FileStorageService implements FileStorageService
{
    private static final Log log = LogFactory.getLog( AmazonS3FileStorageService.class );

    private static final String BUCKET_NAME = "metadatarepo.dhis2.org";

    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private AmazonS3Client amazonS3Client;

    // -------------------------------------------------------------------------
    // Implementation methods
    // -------------------------------------------------------------------------

    @Override
    public FileUploadStatus uploadFile( MultipartFile file ) throws WebMessageException
    {
        FileUploadStatus status = new FileUploadStatus();

        String resourceKey = UUID.randomUUID().toString();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength( Long.valueOf( file.getSize() ));

        PutObjectRequest request;

        try
        {
            request = new PutObjectRequest( BUCKET_NAME, resourceKey, file.getInputStream(), metadata );
            request.setCannedAcl( CannedAccessControlList.PublicRead );

            amazonS3Client.putObject( request );

            status.setUploaded( true );
            status.setDownloadUrl( amazonS3Client.getResourceUrl( BUCKET_NAME, resourceKey ) );
        }
        catch ( AmazonServiceException ase )
        {
            log.error( "Service Error " + ase );

            throw new WebMessageException( WebMessageUtils.conflict( ase.getErrorMessage() ) );
        }
        catch ( AmazonClientException ace )
        {
            log.error( "Client Error " + ace );

            throw new WebMessageException( WebMessageUtils.conflict( ace.getMessage() ) );
        }
        catch ( IOException ioE )
        {
            log.error( "IOException " + ioE );

            throw new WebMessageException( WebMessageUtils.conflict( ioE.getMessage() ) );
        }

        log.info( " File Uploaded " );

        return status;
    }

    @Override
    public void deleteFile( String key )
    {
        amazonS3Client.deleteObject( BUCKET_NAME, key );
    }
}
