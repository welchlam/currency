ALTER TABLE CTLM_TABLE
   ADD CONSTRAINT PK_CTLM_TABLE PRIMARY KEY (TABLE_NAME)
   USING INDEX
   NOVALIDATE;

ALTER TABLE CTLM_TABLE MODIFY (TABLE_NAME          NOT NULL ENABLE);
ALTER TABLE CTLM_TABLE MODIFY (DATACENTER          NOT NULL ENABLE);
ALTER TABLE CTLM_TABLE MODIFY (TABLE_USERDAILY     NOT NULL ENABLE);
ALTER TABLE CTLM_TABLE MODIFY (USED_BY_CODE        NOT NULL ENABLE);
ALTER TABLE CTLM_TABLE MODIFY (ACTIVE_FLAG         NOT NULL ENABLE);
ALTER TABLE CTLM_TABLE MODIFY (CREATED_TSMP        NOT NULL ENABLE);
ALTER TABLE CTLM_TABLE MODIFY (CREATED_BY_NAME     NOT NULL ENABLE);
