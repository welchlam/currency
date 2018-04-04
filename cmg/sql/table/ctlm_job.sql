CREATE TABLE CTLM_JOB
(
  ACTIVE_FROM                   DATE
, ACTIVE_TILL                   DATE
, APPLICATION                   VARCHAR2(30)
, APR                           NUMBER
, AUG                           NUMBER
, AUTHOR                        VARCHAR2(30)
, AUTOARCH                      NUMBER
, CMDLINE                       VARCHAR2(1000)
, CONFCAL                       VARCHAR2(100)
, CONFIRM                       NUMBER
, CRITICAL                      NUMBER
, CYCLIC                        NUMBER
, CYCLIC_TOLERANCE              VARCHAR2(100)
, CYCLIC_TYPE                   VARCHAR2(16)
, DAYS                          VARCHAR2(200)
, DAYSCAL                       VARCHAR2(100)
, DAYS_AND_OR                   VARCHAR2(3)
, DEC                           NUMBER
, DESCRIPTION                   VARCHAR2(1000)
, DOCLIB                        VARCHAR2(100)
, DOCMEM                        VARCHAR2(100)
, FEB                           NUMBER
, GROUP_NAME                    VARCHAR2(100)
, IND_CYCLIC                    VARCHAR2(10)
, INSTREAM_JCL                  VARCHAR2(100)
, CJ_INTERVAL                   VARCHAR2(100)
, JAN                           NUMBER
, JOBNAME                       VARCHAR2(100)
, JUL                           NUMBER
, JUN                           NUMBER
, MAR                           NUMBER
, MAXDAYS                       NUMBER
, MAXRERUN                      NUMBER
, MAXRUNS                       NUMBER
, MAXWAIT                       NUMBER
, MAY                           NUMBER
, MEMLIB                        VARCHAR2(100)
, MEMNAME                       VARCHAR2(100)
, MULTY_AGENT                   VARCHAR2(3)
, NODEID                        VARCHAR2(100)
, NOV                           NUMBER
, OCT                           NUMBER
, JOB_OPTION                    VARCHAR2(11)
, OVERLIB                       VARCHAR2(100)
, OWNER                         VARCHAR2(100)
, PAR                           VARCHAR2(100)
, PARENT_TABLE                  VARCHAR2(100)
, PRIORITY                      VARCHAR2(100)
, RETRO                         NUMBER
, RULE_BASED_CALENDAR_REL       VARCHAR2(100)
, SEP                           NUMBER
, SHIFT                         VARCHAR2(100)
, SHIFTNUM                      VARCHAR2(100)
, SYSDB                         NUMBER
, TASKTYPE                      VARCHAR2(100)
, TIMEFROM                      VARCHAR2(8)
, TIMETO                        VARCHAR2(8)
, TIMEZONE                      VARCHAR2(100)
, USE_INSTREAM_JCL              VARCHAR2(3)
, WEEKDAYS                      VARCHAR2(100)
, WEEKSCAL                      VARCHAR2(100)
, ACTIVE_FLAG                   VARCHAR2(1)
, CREATED_TSMP                  DATE
, CREATED_BY_NAME               VARCHAR2(30)
, AMENDED_TSMP                  DATE
, AMENDED_BY_NAME               VARCHAR2(30)
) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 0 INITRANS 1 MAXTRANS 255
  NOCOMPRESS LOGGING;

