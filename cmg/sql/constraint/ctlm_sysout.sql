ALTER TABLE CTLM_SYSOUT
   ADD CONSTRAINT PK_CTLM_SYSOUT PRIMARY KEY (ACTION_DETAIL_ID,SYSOUT_OPTION,PAR)
   USING INDEX
   NOVALIDATE;

ALTER TABLE CTLM_SYSOUT MODIFY (SYSOUT_OPTION       NOT NULL ENABLE);
ALTER TABLE CTLM_SYSOUT MODIFY (PAR                 NOT NULL ENABLE);
ALTER TABLE CTLM_SYSOUT MODIFY (CREATED_TSMP        NOT NULL ENABLE);
ALTER TABLE CTLM_SYSOUT MODIFY (CREATED_BY_NAME     NOT NULL ENABLE);
