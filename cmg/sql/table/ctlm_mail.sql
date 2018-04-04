CREATE TABLE CTLM_MAIL
(
  ACTION_DETAIL_ID              NUMBER
, ATTACH_SYSOUT                 VARCHAR2(100)
, CC_DEST                       VARCHAR2(100)
, DEST                          VARCHAR2(100)
, MESSAGE                       VARCHAR2(100)
, SUBJECT                       VARCHAR2(100)
, URGENCY                       VARCHAR2(100)
, CREATED_TSMP                  DATE
, CREATED_BY_NAME               VARCHAR2(30)
, AMENDED_TSMP                  DATE
, AMENDED_BY_NAME               VARCHAR2(30)
) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 0 INITRANS 1 MAXTRANS 255
  NOCOMPRESS LOGGING;
