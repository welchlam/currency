CREATE TABLE CTLM_ACTION_DETAIL
(
  ACTION_DETAIL_ID              NUMBER
, ACTION_ID                     NUMBER
, ACTION                        VARCHAR2(100)
, CREATED_TSMP                  DATE
, CREATED_BY_NAME               VARCHAR2(30)
, AMENDED_TSMP                  DATE
, AMENDED_BY_NAME               VARCHAR2(30)
) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 0 INITRANS 1 MAXTRANS 255
  NOCOMPRESS LOGGING;
