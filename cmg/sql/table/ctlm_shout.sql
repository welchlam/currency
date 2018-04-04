CREATE TABLE CTLM_SHOUT
(
  JOB_NAME                      VARCHAR2(100)
, UUID                          VARCHAR2(30)
, ACTION_DETAIL_ID              NUMBER
, DEST                          VARCHAR2(100)
, MESSAGE                       VARCHAR2(100)
, CS_TIME                       VARCHAR2(100)
, URGENCY                       VARCHAR2(100)
, CS_WHEN                       VARCHAR2(100)
, CREATED_TSMP                  DATE
, CREATED_BY_NAME               VARCHAR2(30)
, AMENDED_TSMP                  DATE
, AMENDED_BY_NAME               VARCHAR2(30)
) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 0 INITRANS 1 MAXTRANS 255
  NOCOMPRESS LOGGING;
