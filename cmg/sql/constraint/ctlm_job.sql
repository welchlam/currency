ALTER TABLE CTLM_JOB
   ADD CONSTRAINT PK_CTLM_JOB PRIMARY KEY (JOBNAME)
   USING INDEX
   NOVALIDATE;

ALTER TABLE CTLM_JOB
   ADD CONSTRAINT CK_CTLM_JOB_1 CHECK ( ACTIVE_FLAG IN ('A', 'I', NULL) )
;

ALTER TABLE CTLM_JOB MODIFY (ACTIVE_FLAG         NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (APPLICATION         NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (APR                 NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (AUG                 NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (AUTHOR              NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (AUTOARCH            NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (CONFIRM             NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (CREATED_BY_NAME     NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (CREATED_TSMP        NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (CRITICAL            NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (CYCLIC              NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (DAYS_AND_OR         NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (DEC                 NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (FEB                 NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (GROUP_NAME          NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (JAN                 NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (JUL                 NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (JUN                 NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (MAR                 NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (MAXDAYS             NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (MAXWAIT             NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (MAY                 NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (MULTY_AGENT         NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (NODEID              NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (NOV                 NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (OCT                 NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (OWNER               NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (PARENT_TABLE        NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (SEP                 NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (SHIFT               NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (SHIFTNUM            NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (SYSDB               NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (TASKTYPE            NOT NULL ENABLE);
ALTER TABLE CTLM_JOB MODIFY (USE_INSTREAM_JCL    NOT NULL ENABLE);

