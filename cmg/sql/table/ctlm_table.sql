CREATE TABLE CTLM_TABLE
(
  TABLE_NAME                    VARCHAR2(40)
, DATACENTER                    VARCHAR2(30)
, TABLE_USERDAILY               VARCHAR2(30)
, USED_BY_CODE                  NUMBER
, ACTIVE_FLAG                   VARCHAR2(1)
, CREATED_TSMP                  DATE
, CREATED_BY_NAME               VARCHAR2(30)
, AMENDED_TSMP                  DATE
, AMENDED_BY_NAME               VARCHAR2(30)
) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 0 INITRANS 1 MAXTRANS 255
  NOCOMPRESS LOGGING;
