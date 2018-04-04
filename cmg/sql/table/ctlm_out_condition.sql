CREATE TABLE CTLM_OUT_CONDITION
(
  ACTION_DETAIL_ID              NUMBER
, JOB_NAME                      VARCHAR2(100)
, PARENT_JOB                    VARCHAR2(100)
, CHILD_JOB                     VARCHAR2(100)
, TYPE                          VARCHAR2(100)
, ODATE                         VARCHAR2(100)
, SIGN                          VARCHAR2(10)
, CREATED_TSMP                  DATE
, CREATED_BY_NAME               VARCHAR2(30)
, AMENDED_TSMP                  DATE
, AMENDED_BY_NAME               VARCHAR2(30)
) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 0 INITRANS 1 MAXTRANS 255
  NOCOMPRESS LOGGING;
