CREATE TABLE CTLM_IN_CONDITION
(
  JOB_NAME                      VARCHAR2(100)
, PARENT_JOB                    VARCHAR2(100)
, CHILD_JOB                     VARCHAR2(100)
, CONDITION_ORDER               NUMBER
, TYPE                          VARCHAR2(100)
, AND_OR                        VARCHAR2(100)
, ODATE                         VARCHAR2(100)
, OP                            VARCHAR2(100)
, CREATED_TSMP                  DATE
, CREATED_BY_NAME               VARCHAR2(30)
, AMENDED_TSMP                  DATE
, AMENDED_BY_NAME               VARCHAR2(30)
) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 0 INITRANS 1 MAXTRANS 255
  NOCOMPRESS LOGGING;
