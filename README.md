# dhis2-metadata-package-repo
Metadata package repository
# Setup
## Clone the repository
```bash
git@github.com:dhis2/dhis2-metadata-package-repo.git
```
## Create back-end config file
Create config file called `metadatarepo.conf` in `/opt/hisp/metadatapackage` with the following config

> Note to change the credentials and secrets etc.

```
# JDBC driver connection URL
connection.url=jdbc:postgresql:metadatarepo_db

# Database username
connection.username=<database username>

# Database password
connection.password=<database password>

#AmazonS3 access id
access.id=<AmazonS3 id>

#AmazonS3 secret key
secret.key=<AmazonS3 secret>

auth0.domain=<auth0 domain>
auth0.issuer=<auth0 certificate issuer>
auth0.clientId=<auth0 client id>
auth0.clientSecret=<auth0 client secret>
auth0.securedRoute=/secured/*
auth0.base64EncodedSecret=false 
auth0.authorityStrategy=ROLES
auth0.defaultAuth0ApiSecurityEnabled=false
auth0.signingAlgorithm=HS256
```
## Create postgres database (if you do not have one yet)
```sql
CREATE DATABASE metadatarepo_db OWNER metadata;
```
