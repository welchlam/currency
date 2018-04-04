CREATE TABLE CTLM_C_CONDITION
(
  UUID                          VARCHAR2(30)
, JOB_NAME                      VARCHAR2(100)
, ACTION_DETAIL_ID              NUMBER
, NAME                          VARCHAR2(100)
, AND_OR                        VARCHAR2(100)
, ODATE                         VARCHAR2(100)
, SIGN                          VARCHAR2(3)
, CREATED_TSMP                  DATE
, CREATED_BY_NAME               VARCHAR2(30)
, AMENDED_TSMP                  DATE
, AMENDED_BY_NAME               VARCHAR2(30)
) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 0 INITRANS 1 MAXTRANS 255
  NOCOMPRESS LOGGING;
